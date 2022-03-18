package com.hunk.route.infrastructure.messaging.event;

import java.util.Map;
/**
 * @author hunk
 * @date 2022/3/15
 *     <p>
 */
public interface CustomEventBus {

    Map<String, Object> publish(CustomEvent event) throws CustomEventException;

    void register(CustomEventListener<?> paramEventListener);

    void unRegister(CustomEventListener<?> paramEventListener);

}
