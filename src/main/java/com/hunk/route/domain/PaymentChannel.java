package com.hunk.route.domain;

import lombok.Data;

import javax.persistence.Embeddable;

/**
 * @author hunk
 * @date 2022/2/23
 *     <p>路由对象
 */
@Data
@Embeddable
public class PaymentChannel {

    private String routeInterface;

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
