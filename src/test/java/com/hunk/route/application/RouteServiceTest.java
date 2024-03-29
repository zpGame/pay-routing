package com.hunk.route.application;

import com.hunk.route.ApplicationTests;
import com.hunk.route.domain.model.channel.PaymentChannel;
import com.hunk.route.domain.model.em.ChannelE;
import com.hunk.route.domain.model.em.ServiceE;
import com.hunk.route.domain.model.route.EffectiveTime;
import com.hunk.route.domain.model.route.RouteChannel;
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
        String ruleId = "1";
        EffectiveTime effectiveTime = new EffectiveTime(LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
        String createUser = "system";
        RouteChannel route = routeService.createRoute(paymentChannel, ruleId, effectiveTime, createUser);
        System.out.println(route);
    }

    @Test
    public void findById() {
    }

    @Test
    public void reviseInfo() {
    }
}
