package com.hunk.route.domain;

import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author hunk
 * @date 2022/2/17
 * <p> 通道成本 目前是单笔收费
 */
@Entity
@Getter
@Table(name = "channel_cost")
public class ChannelCost extends BaseEntity{

    @Embedded
    private PaymentChannel paymentChannel;

    @Column(name = "rate", length = 12)
    private String rate;

    public ChannelCost(PaymentChannel paymentChannel, String rate) {
        this.paymentChannel = paymentChannel;
        this.rate = rate;
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
