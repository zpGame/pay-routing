package com.hunk.route.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hunk
 * @date 2022/2/24
 *     <p>
 */
public interface BankInfoRepository extends JpaRepository<BankInfo, Long> {

    /**
     * 查询银行信息根据bankId
     *
     * @param bankId id
     * @return BankInfo
     */
    BankInfo findBankInfoByBankId(String bankId);
}
