package com.hunk.route.domain.event;

import com.google.common.eventbus.Subscribe;
import com.hunk.route.infrastructure.messaging.event.EventSupportListen;

import java.util.Map;

/**
 * @author hunk
 * @date 2022/4/5
 *     <p>
 */
public class RouteRuleListen extends EventSupportListen<RouteRuleEvent> {
    @Override
    @Subscribe
    public Map<String, Object> onEvent(RouteRuleEvent paramE) {
        return null;
    }
}
