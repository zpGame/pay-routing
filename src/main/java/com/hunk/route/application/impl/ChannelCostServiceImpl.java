package com.hunk.route.application.impl;

import com.hunk.route.application.ChannelCostService;
import com.hunk.route.domain.*;

import java.util.Optional;

/**
 * @author hunk
 * @date 2022/3/23
 *     <p>
 */
public class ChannelCostServiceImpl implements ChannelCostService {

    private final ChannelCostRepository channelCostRepository;

    public ChannelCostServiceImpl(ChannelCostRepository channelCostRepository) {
        this.channelCostRepository = channelCostRepository;
    }

    @Override
    public ChannelCost createChannelCost(
            PaymentChannel paymentChannel, String rate, String createUser) {
        CreateInfo createInfo = CreateInfo.createInfo(createUser);
        ChannelCost channelCost = ChannelCost.createChannelCost(paymentChannel, rate, createInfo);
        return channelCostRepository.save(channelCost);
    }

    @Override
    public Optional<ChannelCost> findById(Long channelCostId) {
        return channelCostRepository.findById(channelCostId);
    }

    @Override
    public ChannelCost reviseInfo(
            Long channelCostId, PaymentChannel paymentChannel, String rate, String modifyUser) {
        ChannelCost channelCost =
                channelCostRepository
                        .findById(channelCostId)
                        .orElseThrow(() -> new BankInfoNotFoundException(channelCostId));
        CreateInfo createInfo = channelCost.getCreateInfo().reviseInfo(modifyUser);
        ChannelCost changeCreateInfo =
                channelCost
                        .changePaymentChannel(paymentChannel)
                        .changeRate(rate)
                        .changeCreateInfo(createInfo);
        return channelCostRepository.save(changeCreateInfo);
    }
}
