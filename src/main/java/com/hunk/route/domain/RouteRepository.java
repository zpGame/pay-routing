package com.hunk.route.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hunk
 * @date 2022/2/20
 *     <p>
 */
public interface RouteRepository extends JpaRepository<RouteChannel, Long> {

    RouteChannel findRouteChannelByChannelId(String channelId);

}
