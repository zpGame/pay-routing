package com.hunk.route.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author hunk
 * @date 2022/3/23
 *     <p>
 */
public interface ChannelCostRepository extends JpaRepository<ChannelCost, Long> {

    /**
     * 根据路由信息查询成本集
     *
     * @param paymentChannels 路由信息
     * @return list
     */
    List<ChannelCost> findByPaymentChannelIn(List<PaymentChannel> paymentChannels);
}
