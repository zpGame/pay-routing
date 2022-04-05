package com.hunk.route.domain.event;

import com.hunk.route.infrastructure.messaging.event.CustomEvent;

/**
 * @author hunk
 * @date 2022/4/2
 *     <p>
 */
public class ResultWithDomainEvents<A, E extends CustomEvent> {

    public final A result;

    public final E events;

    public ResultWithDomainEvents(A result, E events) {
        this.result = result;
        this.events = events;
    }
}
