package com.hunk.route.domain.event;

import com.google.common.eventbus.Subscribe;
import com.hunk.route.infrastructure.messaging.event.EventSupportListen;

import java.util.Map;

/**
 * @author hunk
 * @date 2022/4/2
 * <p>
 */
public class MerchantRouteListen extends EventSupportListen<MerchantRouteEvent> {

    @Override
    @Subscribe
    public Map<String, Object> onEvent(MerchantRouteEvent paramE) {
        System.out.println(paramE);
        return null;
    }
}
