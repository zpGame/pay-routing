package com.hunk.route.infrastructure.messaging.event;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author hunk
 * @date 2022/3/16
 *     <p>
 */
public abstract class EventSupportListener<E extends Event>
        implements EventListener<E>, InitializingBean {

    @Autowired private EventBus eventBus;

    protected Map<String, Object> publish(E event) {
        return this.eventBus.publish(event);
    }

    @Override
    public void afterPropertiesSet() {
        this.eventBus.register(this);
    }
}
