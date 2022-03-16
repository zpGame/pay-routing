package com.hunk.route.infrastructure.messaging.event;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;

/**
 * @author hunk
 * @date 2022/3/15
 *     <p>
 */
@Getter
@Setter
public abstract class Event {

    private String eventName;

    private Map<String, Object> context = Maps.newHashMap();

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("eventName", eventName)
                .append("context", context)
                .toString();
    }
}
