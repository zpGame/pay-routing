package com.hunk.route.infrastructure.messaging.event;

import java.util.Map;
/**
 * @author hunk
 * @date 2022/3/15
 *     <p>
 */
public interface EventBus {

    Map<String, Object> publish(Event event) throws EventException;

    void register(EventListener<?> paramEventListener);

    void unRegister(EventListener<?> paramEventListener);

}
