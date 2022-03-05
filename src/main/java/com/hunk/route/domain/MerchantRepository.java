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

    /**
     * Query
     *
     * @param merchantNo 商户号
     * @return MerchantRoute
     */
    @Query("select s from MerchantRoute s where s.merchantNo =: merchantNo")
    MerchantRoute findByMerchantNo(@Param("merchantNo") String merchantNo);
}
