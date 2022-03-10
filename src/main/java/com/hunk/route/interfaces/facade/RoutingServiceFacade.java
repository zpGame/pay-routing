package com.hunk.route.interfaces.facade;

import com.hunk.route.interfaces.facade.dto.RouteInfoDTO;
import com.hunk.route.interfaces.web.command.ObtainRouteCommand;

/**
 * @author hunk
 * @date 2022/2/27
 *     <p>
 */
public interface RoutingServiceFacade {

    /**
     * 获取信息
     *
     * @param command 请求参数
     * @return info
     */
    RouteInfoDTO obtainRoute(ObtainRouteCommand command);
}
