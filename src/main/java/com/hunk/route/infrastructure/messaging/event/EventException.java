package com.hunk.route.infrastructure.messaging.event;

/**
 * @author hunk
 * @date 2022/3/16
 *     <p>
 */
public class EventException extends RuntimeException {

    public EventException() {}

    public EventException(String message) {
        super(message);
    }

    public EventException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventException(Throwable cause) {
        super(cause);
    }

    protected EventException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
