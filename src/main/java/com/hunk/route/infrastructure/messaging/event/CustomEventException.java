package com.hunk.route.infrastructure.messaging.event;

/**
 * @author hunk
 * @date 2022/3/16
 *     <p>
 */
public class CustomEventException extends RuntimeException {

    public CustomEventException() {}

    public CustomEventException(String message) {
        super(message);
    }

    public CustomEventException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomEventException(Throwable cause) {
        super(cause);
    }

    protected CustomEventException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
