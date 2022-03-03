package com.hunk.route.interfaces.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author hunk
 * @date 2022/2/27
 *     <p>
 */
@Controller
public class RouteController {

    @GetMapping()
    public String index() {
        return "/index";
    }
}
