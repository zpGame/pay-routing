package com.hunk.route.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author hunk
 * @date 2022/2/17
 *     <p>
 */
public interface MerchantRepository extends JpaRepository<MerchantRoute, Long> {

    MerchantRoute findMerchantRouteByMerchantNo(String merchantNo);
}
