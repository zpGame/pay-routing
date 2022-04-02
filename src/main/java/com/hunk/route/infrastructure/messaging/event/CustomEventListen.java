package com.hunk.route.infrastructure.messaging.event;

import java.util.Map;

/**
 * @author hunk
 * @date 2022/3/16
 *     <p>
 */
public interface CustomEventListen<E> {

    /**
     * 执行流程
     *
     * @param paramE 参数
     * @return map
     */
    Map<String, Object> onEvent(E paramE);
}
