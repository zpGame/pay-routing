package com.hunk.route.domain;

import com.hunk.route.domain.model.em.ChannelE;
import com.hunk.route.domain.model.em.ServiceE;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        PaymentChannel channel = (PaymentChannel) obj;

        return new EqualsBuilder().append(routeInterface, channel.routeInterface).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(routeInterface).append(routeName).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("routeInterface", routeInterface)
                .append("routeName", routeName)
                .toString();
    }
}
