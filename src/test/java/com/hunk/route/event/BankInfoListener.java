package com.hunk.route.event;

import com.google.common.eventbus.Subscribe;
import com.hunk.route.infrastructure.messaging.event.EventSupportListener;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author hunk
 * @date 2022/3/29
 * <p>
 */
@Service
public class BankInfoListener extends EventSupportListener<BankInfoEvent> {

    @Subscribe
    @Override
    public Map<String, Object> onEvent(BankInfoEvent paramE) {
        System.out.println(paramE);
        return null;
    }

}
