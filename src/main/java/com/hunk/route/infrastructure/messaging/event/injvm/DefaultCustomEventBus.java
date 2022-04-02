package com.hunk.route.infrastructure.messaging.event.injvm;

import com.google.common.eventbus.EventBus;
import com.hunk.route.infrastructure.messaging.event.CustomEvent;
import com.hunk.route.infrastructure.messaging.event.CustomEventBus;
import com.hunk.route.infrastructure.messaging.event.CustomEventException;
import com.hunk.route.infrastructure.messaging.event.CustomEventListen;

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
        try {
            this.eventBus.post(customEvent);
        } catch (Exception e) {
            throw new CustomEventException("publish event error", e);
        }
        return customEvent.getContext();
    }

    @Override
    public void register(CustomEventListen<?> eventListener) {
        this.eventBus.register(eventListener);
    }

    @Override
    public void unRegister(CustomEventListen<?> eventListener) {
        this.eventBus.unregister(eventListener);
    }
}
