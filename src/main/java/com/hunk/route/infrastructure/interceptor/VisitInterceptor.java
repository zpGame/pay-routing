package com.hunk.route.infrastructure.interceptor;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            HandlerMethod h = (HandlerMethod) handler;
            String sb =
                    String.format(
                            "请求信息-URI:{%s};class:{%s};Method:{%s};Params:%s",
                            request.getRequestURI(),
                            h.getBean().getClass().getSimpleName(),
                            h.getMethod().getName(),
                            JSONObject.toJSONString(request.getParameterMap()));
            log.info(sb);
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
        if (handler instanceof HandlerMethod) {
            HandlerMethod h = (HandlerMethod) handler;
            String sb =
                    String.format(
                            "返回信息-URI:{%s};class:{%s};Method:{%s};Params:%s;CostTime:{%sms}",
                            request.getRequestURI(),
                            h.getBean().getClass().getSimpleName(),
                            h.getMethod().getName(),
                            JSONObject.toJSONString(request.getParameterMap()),
                            System.currentTimeMillis() - startTime);
            log.info(sb);
        }
    }

    @Override
    public void afterCompletion(
            HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        MDC.remove(MDC_TRACE);
    }
}
