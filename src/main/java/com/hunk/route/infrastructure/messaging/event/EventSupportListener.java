package com.hunk.route.infrastructure.messaging.event;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author hunk
 * @date 2022/3/16
 *     <p>
 */
public abstract class EventSupportListener<E extends CustomEvent>
        implements CustomEventListener<E>, InitializingBean {

    @Resource
    private CustomEventBus eventBus;

    protected Map<String, Object> publish(E event) {
        return this.eventBus.publish(event);
    }

    @Override
    public void afterPropertiesSet() {
        this.eventBus.register(this);
    }
}
