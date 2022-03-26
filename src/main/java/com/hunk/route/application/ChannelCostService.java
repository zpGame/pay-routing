package com.hunk.route.application;

import com.hunk.route.domain.ChannelCost;
import com.hunk.route.domain.PaymentChannel;

import java.util.Optional;

/**
 * @author hunk
 * @date 2022/3/23
 *     <p>
 */
public interface ChannelCostService {

    /**
     * 创建对象
     *
     * @param paymentChannel 路由信息
     * @param rate 费率
     * @param createUser 创建人
     * @return ChannelCost
     */
    ChannelCost createChannelCost(PaymentChannel paymentChannel, String rate, String createUser);

    /**
     * 根据ID查询对象
     *
     * @param channelCostId id
     * @return Optional
     */
    Optional<ChannelCost> findById(Long channelCostId);

    /**
     * 修改信息
     *
     * @param channelCostId id
     * @param paymentChannel 路由信息
     * @param rate 费率
     * @param modifyUser 修改人
     * @return ChannelCost
     */
    ChannelCost reviseInfo(
            Long channelCostId, PaymentChannel paymentChannel, String rate, String modifyUser);
}
