package com.hunk.route.domain.event;

import com.hunk.route.infrastructure.messaging.event.CustomEvent;

/**
 * @author hunk
 * @date 2022/4/2
 *     <p>
 */
public class ResultWithDomainEvents<A, E extends CustomEvent> {

    public final A result;

    public final E event;

    public ResultWithDomainEvents(A result, E event) {
        this.result = result;
        this.event = event;
    }
}
