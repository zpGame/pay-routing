package com.hunk.route.application;

import com.hunk.route.domain.MerchantRoute;

import java.util.Optional;
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
     * @param createUser 创建人
     * @return MerchantRoute
     */
    MerchantRoute createMerchant(
            String merchantNo, String merchantName, Set<Long> routeIds, String createUser);

    /**
     * ID查询商户
     *
     * @param merchantNo ID
     * @return Optional
     */
    MerchantRoute findByMerchantNo(String merchantNo);

    /**
     * 修改商户信息
     *
     * @param merchantId 商户ID
     * @param merchantNo 商户号
     * @param merchantName 商户名称
     * @param routeIds 路由ID
     * @param modifyUser 修改人
     * @return MerchantRoute
     */
    MerchantRoute reviseInfo(
            Long merchantId,
            String merchantNo,
            String merchantName,
            Set<Long> routeIds,
            String modifyUser);
}
