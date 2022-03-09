package com.hunk.route.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author hunk
 * @date 2022/2/23
 *     <p>路由对象
 */
@Getter
@Embeddable
public class PaymentChannel {

    @Column(name = "route_interface", length = 32)
    private String routeInterface;

    @Column(name = "route_name", length = 32)
    private String routeName;

    public PaymentChannel() {}

    public PaymentChannel(ChannelE channelE, ServiceE serviceE) {
        this.routeInterface = compose(channelE.getChannel(), serviceE.getService());
        this.routeName = compose(channelE.getChannelName(), serviceE.getServiceName());
    }

    private String compose(String a, String b) {
        return a + "-" + b;
    }
}
