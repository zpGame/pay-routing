package com.hunk.route.domain;

import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author hunk
 * @date 2022/2/17
 *     <p>通道成本 目前是单笔收费
 *     <p>通道成本作为目前优先级，前端页面必填
 */
@Entity
@Getter
@Table(name = "channel_cost")
public class ChannelCost extends BaseEntity {

    @Embedded private PaymentChannel paymentChannel;

    @Column(name = "rate", length = 12)
    private String rate;

    public static ChannelCost createChannelCost(
            PaymentChannel paymentChannel, String rate, CreateInfo createInfo) {
        return new ChannelCost(paymentChannel, rate, createInfo);
    }

    public ChannelCost() {}

    public ChannelCost(PaymentChannel paymentChannel, String rate, CreateInfo createInfo) {
        this.paymentChannel = paymentChannel;
        this.rate = rate;
        super.createInfo = createInfo;
    }

    public ChannelCost changePaymentChannel(PaymentChannel paymentChannel) {
        this.paymentChannel = paymentChannel;
        return this;
    }

    public ChannelCost changeRate(String rate) {
        this.rate = rate;
        return this;
    }

    public ChannelCost changeCreateInfo(CreateInfo createInfo) {
        this.createInfo = createInfo;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        ChannelCost channelCost = (ChannelCost) obj;

        return new EqualsBuilder().append(id, channelCost.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(paymentChannel).append(rate).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("createInfo", createInfo)
                .append("paymentChannel", paymentChannel)
                .append("rate", rate)
                .toString();
    }
}
