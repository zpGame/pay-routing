package com.hunk.route.event;

import com.hunk.route.infrastructure.messaging.event.CustomEvent;
import lombok.Data;

/**
 * @author hunk
 * @date 2022/3/29
 * <p>
 */
@Data
public class BankInfoEvent extends CustomEvent {

    private String name;

}
