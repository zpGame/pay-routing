package com.hunk.route.interfaces.web;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zhao Peng
 * @date 2022/4/25
 *     <p>
 */
public class BaseController {

    protected <T> void pageWrite(HttpServletResponse response, long total, List<T> infoDtoS)
            throws IOException {
        Map<String, Object> ret = new HashMap<>(16);
        ret.put("total", total);
        ret.put("rows", infoDtoS);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(JSONObject.toJSONString(ret));
    }

    protected void write(HttpServletResponse response) throws IOException {
        this.write(response, null);
    }

    protected void write(HttpServletResponse response, String msg) throws IOException {
        Map<String, Object> objectMap = new HashMap<>(16);
        objectMap.put("success", true);
        if (StringUtils.isNotBlank(msg)) {
            objectMap.put("errorMsg", msg);
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(JSONObject.toJSONString(objectMap));
    }
}
