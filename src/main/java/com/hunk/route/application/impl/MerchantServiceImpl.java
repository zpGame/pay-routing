package com.hunk.route.application.impl;

import com.hunk.route.application.MerchantService;
import com.hunk.route.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
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

    public MerchantServiceImpl(
            MerchantRepository merchantRepository, RouteRepository routeRepository) {
        this.merchantRepository = merchantRepository;
        this.routeRepository = routeRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MerchantRoute createMerchant(
            String merchantNo, String merchantName, Set<Long> routeIds, String createUser) {
        Set<RouteChannel> routeChannels =
                routeIds.stream()
                        .map(routeRepository::findById)
                        .map(route -> route.orElse(null))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet());
        CreateInfo createInfo = CreateInfo.createInfo(createUser);
        MerchantRoute merchantRoute =
                MerchantRoute.createMerchant(merchantNo, merchantName, routeChannels, createInfo);
        return merchantRepository.save(merchantRoute);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MerchantRoute findByMerchantNo(String merchantNo) {
        MerchantRoute merchantRoute = merchantRepository.findByMerchantNo(merchantNo);
        return merchantRoute;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MerchantRoute reviseInfo(
            Long merchantId,
            String merchantNo,
            String merchantName,
            Set<Long> routeIds,
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
