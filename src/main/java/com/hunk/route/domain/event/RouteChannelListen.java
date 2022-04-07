package com.hunk.route.domain.event;

import com.google.common.eventbus.Subscribe;
import com.hunk.route.infrastructure.messaging.event.EventSupportListen;

import java.util.Map;

/**
 * @author hunk
 * @date 2022/4/6
 * <p>
 */
public class RouteChannelListen extends EventSupportListen<RouteChannelEvent> {
    @Override
    @Subscribe
    public Map<String, Object> onEvent(RouteChannelEvent paramE) {
        return null;
    }
}
