package com.hunk.route.interfaces.facade;

import com.hunk.route.interfaces.facade.dto.RouteInfoDTO;
import com.hunk.route.interfaces.web.ObtainRouteCommand;

/**
 * @author hunk
 * @date 2022/2/27
 *     <p>
 */
public interface RoutingServiceFacade {

    RouteInfoDTO obtainRoute(ObtainRouteCommand command);
}
