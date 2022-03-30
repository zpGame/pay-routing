package com.hunk.route.event;

import com.hunk.route.ApplicationTests;
import com.hunk.route.infrastructure.messaging.event.injvm.DefaultCustomEventBus;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author hunk
 * @date 2022/3/29
 * <p>
 */
public class EventTest extends ApplicationTests {

    @Resource
    private DefaultCustomEventBus defaultCustomEventBus;

    @Test
    public void bank() throws InterruptedException {
        BankInfoEvent bankInfoEvent = new BankInfoEvent();
        bankInfoEvent.setName("test");
        defaultCustomEventBus.publish(bankInfoEvent);
        TimeUnit.SECONDS.sleep(10);
    }

}
