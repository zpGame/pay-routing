package com.hunk.route.infrastructure.messaging.event;

import java.util.Map;

/**
 * @author hunk
 * @date 2022/3/16
 *     <p>
 */
public interface CustomEventListener<E> {

    Map<String, Object> onEvent(E paramE);

}
