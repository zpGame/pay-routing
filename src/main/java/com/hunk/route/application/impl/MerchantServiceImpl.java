package com.hunk.route.application.impl;

import com.hunk.route.application.MerchantService;
import com.hunk.route.domain.event.MerchantRouteEvent;
import com.hunk.route.domain.event.ResultWithDomainEvents;
import com.hunk.route.domain.model.CreateInfo;
import com.hunk.route.domain.model.merchant.MerchantNotFoundException;
import com.hunk.route.domain.model.merchant.MerchantRepository;
import com.hunk.route.domain.model.merchant.MerchantRoute;
import com.hunk.route.domain.model.route.RouteChannel;
import com.hunk.route.domain.model.route.RouteRepository;
import com.hunk.route.infrastructure.messaging.event.CustomEventBus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author hunk
 * @date 2022/2/25
 *     <p>
 */
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository merchantRepository;
    private final RouteRepository routeRepository;
    private final CustomEventBus customEventBus;

    public MerchantServiceImpl(
            MerchantRepository merchantRepository,
            RouteRepository routeRepository,
            CustomEventBus customEventBus) {
        this.merchantRepository = merchantRepository;
        this.routeRepository = routeRepository;
        this.customEventBus = customEventBus;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MerchantRoute createMerchant(
            String merchantNo, String merchantName, Set<String> routeIds, String createUser) {
        Set<RouteChannel> routeChannels =
                routeIds.stream()
                        .map(routeRepository::findRouteChannelByChannelId)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet());
        CreateInfo createInfo = CreateInfo.createInfo(createUser);
        ResultWithDomainEvents<MerchantRoute, MerchantRouteEvent> domainEvents =
                MerchantRoute.createMerchant(merchantNo, merchantName, routeChannels, createInfo);
        MerchantRoute merchantRoute = domainEvents.result;
        merchantRepository.save(merchantRoute);
        customEventBus.publish(domainEvents.event);
        return merchantRoute;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MerchantRoute findByMerchantNo(String merchantNo) {
        return merchantRepository.findMerchantRouteByMerchantNo(merchantNo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MerchantRoute reviseInfo(
            Long merchantId,
            String merchantNo,
            String merchantName,
            List<Long> routeIds,
            String modifyUser) {
        MerchantRoute merchantRoute =
                merchantRepository
                        .findById(merchantId)
                        .orElseThrow(() -> new MerchantNotFoundException(merchantId));
        CreateInfo createInfo = merchantRoute.getCreateInfo().reviseInfo(modifyUser);
        Set<RouteChannel> routeChannels =
                routeIds.stream()
                        .map(routeRepository::findById)
                        .map(route -> route.orElse(null))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet());
        MerchantRoute changeMerchantRoute =
                merchantRoute
                        .changeMerchantNo(merchantNo)
                        .changeMerchantName(merchantName)
                        .changeRouteChannels(routeChannels)
                        .changeCreateInfo(createInfo);
        return merchantRepository.save(changeMerchantRoute);
    }
}
