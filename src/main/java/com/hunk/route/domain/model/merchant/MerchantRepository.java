package com.hunk.route.domain.model.merchant;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hunk
 * @date 2022/2/17
 *     <p>
 */
public interface MerchantRepository extends JpaRepository<MerchantRoute, Long> {

    MerchantRoute findMerchantRouteByMerchantNo(String merchantNo);
}
