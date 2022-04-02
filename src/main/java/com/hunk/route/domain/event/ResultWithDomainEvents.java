package com.hunk.route.domain.event;

import com.hunk.route.infrastructure.messaging.event.CustomEvent;

import java.util.Arrays;
import java.util.List;

/**
 * @author hunk
 * @date 2022/4/2
 *     <p>
 */
public class ResultWithDomainEvents<A, E extends CustomEvent> {

    public final A result;
    public final List<E> events;

    public ResultWithDomainEvents(A result, List<E> events) {
        this.result = result;
        this.events = events;
    }

    @SafeVarargs
    public ResultWithDomainEvents(A result, E... events) {
        this.result = result;
        this.events = Arrays.asList(events);
    }
}
