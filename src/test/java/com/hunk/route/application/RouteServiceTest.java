package com.hunk.route.application;

import com.hunk.route.ApplicationTests;
import com.hunk.route.domain.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * @author hunk
 * @date 2022/2/28
 * <p>
 */
public class RouteServiceTest  extends ApplicationTests {

    @Autowired
    private RouteService routeService;

    @Test
    public void createRoute() {
        PaymentChannel paymentChannel = new PaymentChannel(ChannelE.UNION, ServiceE.WITHHOLD);
        long ruleId = 1L;
        int priority = 1;
        EffectiveTime effectiveTime = new EffectiveTime(LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
        String createUser = "system";
        RouteChannel route = routeService.createRoute(paymentChannel, ruleId, priority, effectiveTime, createUser);
        System.out.println(route);
    }

    @Test
    public void findById() {
    }

    @Test
    public void reviseInfo() {
    }
}
