package com.hunk.route.interfaces.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hunk
 * @date 2022/2/27
 *     <p>
 */
@RestController
public class RouteController {

    @GetMapping()
    public String index() {
        return "/index";
    }
}
