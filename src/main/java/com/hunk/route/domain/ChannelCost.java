package com.hunk.route.domain;

import lombok.Getter;

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

    @Column(name = "merchant_name", length = 56)
    private String rate;

}
