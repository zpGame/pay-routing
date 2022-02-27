package com.hunk.route.application;

import com.hunk.route.domain.MerchantRoute;

import java.util.Set;

/**
 * @author hunk
 * @date 2022/2/25
 *     <p>
 */
public interface MerchantService {

    /**
     * 创建商户关系
     *
     * @param merchantNo 商户号
     * @param merchantName 商户名称
     * @param routeIds 路由ID集合
     * @return MerchantRoute
     */
    MerchantRoute createMerchant(String merchantNo, String merchantName, Set<Long> routeIds);
}
