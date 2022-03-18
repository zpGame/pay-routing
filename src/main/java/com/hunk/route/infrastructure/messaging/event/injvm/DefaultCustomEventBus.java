package com.hunk.route.infrastructure.messaging.event.injvm;

import com.google.common.collect.Maps;
import com.google.common.eventbus.EventBus;
import com.hunk.route.infrastructure.messaging.event.CustomEvent;
import com.hunk.route.infrastructure.messaging.event.CustomEventBus;
import com.hunk.route.infrastructure.messaging.event.CustomEventException;
import com.hunk.route.infrastructure.messaging.event.CustomEventListener;

import java.util.Map;

/**
 * @author hunk
 * @date 2022/3/18
 *     <p>
 */
public class DefaultCustomEventBus implements CustomEventBus {

    private final EventBus eventBus = new EventBus();

    @Override
    public Map<String, Object> publish(CustomEvent customEvent) throws CustomEventException {
        Map<String, Object> result = Maps.newHashMap();
        try {
            this.eventBus.post(customEvent);
        } catch (Exception e) {
            throw new CustomEventException("publish event error", e);
        }
        return result;
    }

    @Override
    public void register(CustomEventListener<?> eventListener) {
        this.eventBus.register(eventListener);
    }

    @Override
    public void unRegister(CustomEventListener<?> eventListener) {
        this.eventBus.unregister(eventListener);
    }

}
