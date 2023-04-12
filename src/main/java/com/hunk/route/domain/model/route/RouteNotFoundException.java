package com.hunk.route.domain.model.route;

/**
 * @author hunk
 * @date 2022/2/28
 *     <p>
 */
public class RouteNotFoundException extends RuntimeException {

    public RouteNotFoundException(long routeId) {
        super("Route not found with id " + routeId);
    }
}
