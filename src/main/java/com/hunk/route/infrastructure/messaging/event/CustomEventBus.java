package com.hunk.route.infrastructure.messaging.event;

import java.util.Map;
/**
 * @author hunk
 * @date 2022/3/15
 *     <p>
 */
public interface CustomEventBus {

    /**
     * 发布一个消息
     *
     * @param event 事件
     * @return map
     * @throws CustomEventException 发布异常
     */
    Map<String, Object> publish(CustomEvent event) throws CustomEventException;

    /**
     * 注册eventBus
     *
     * @param paramEventListener 监听器
     */
    void register(CustomEventListener<?> paramEventListener);

    /**
     * 取消注册eventBus
     *
     * @param paramEventListener 监听器
     */
    void unRegister(CustomEventListener<?> paramEventListener);
}
