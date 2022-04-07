package com.hunk.route.event;

import com.hunk.route.ApplicationTests;
import com.hunk.route.domain.event.MerchantRouteEvent;
import com.hunk.route.infrastructure.messaging.event.injvm.DefaultCustomEventBus;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author hunk
 * @date 2022/3/29
 *     <p>
 */
public class EventTest extends ApplicationTests {

    @Resource DefaultCustomEventBus defaultCustomEventBus;

    @Test
    public void merchantRoute() throws InterruptedException {
        MerchantRouteEvent bankInfoEvent =
                new MerchantRouteEvent("", "999999", "山外山", Collections.singleton("2"));
        defaultCustomEventBus.publish(bankInfoEvent);
        TimeUnit.SECONDS.sleep(10);
    }
}
