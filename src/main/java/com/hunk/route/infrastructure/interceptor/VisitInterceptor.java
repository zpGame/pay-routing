package com.hunk.route.infrastructure.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

/**
 * @author hunk
 * @date 2022/3/12
 *     <p>
 */
@Slf4j
@Component
public class VisitInterceptor implements HandlerInterceptor {

    private static final String MDC_TRACE = "FullLink";

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        MDC.put(MDC_TRACE, UUID.randomUUID().toString().replace("-", ""));
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        if (handler instanceof HandlerMethod) {
            StringBuilder sb = new StringBuilder(1000);
            sb.append("--start-----------------------------------\n");
            HandlerMethod h = (HandlerMethod) handler;
            sb.append("URI       : ").append(request.getRequestURI()).append("\n");
            sb.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");
            sb.append("Method    : ").append(h.getMethod().getName()).append("\n");
            sb.append("Params    : ")
                    .append(getParamString(request.getParameterMap()))
                    .append("\n");
            log.info(sb.toString());
        }
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView)
            throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        if (handler instanceof HandlerMethod) {
            StringBuilder sb = new StringBuilder(1000);
            HandlerMethod h = (HandlerMethod) handler;
            sb.append("URI       : ").append(request.getRequestURI()).append("\n");
            sb.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");
            sb.append("Method    : ").append(h.getMethod().getName()).append("\n");
            sb.append("Params    : ")
                    .append(getParamString(request.getParameterMap()))
                    .append("\n");
            sb.append("cost time  : ").append(executeTime).append("ms").append("\n");
            sb.append("--end------------------------------------\n");
            log.info(sb.toString());
        }
    }

    private String getParamString(Map<String, String[]> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String[]> e : map.entrySet()) {
            sb.append(e.getKey()).append("=");
            String[] value = e.getValue();
            if (value != null && value.length == 1) {
                sb.append(value[0]).append("\t");
            } else {
                sb.append(Arrays.toString(value)).append("\t");
            }
        }
        return sb.toString();
    }

    @Override
    public void afterCompletion(
            HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        MDC.remove(MDC_TRACE);
    }
}
