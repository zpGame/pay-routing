package com.hunk.route.application;

import com.hunk.route.domain.BankInfo;
import com.hunk.route.domain.BankName;
import com.hunk.route.domain.CardType;

import java.util.Optional;

/**
 * @author hunk
 * @date 2022/2/25
 *     <p>
 */
public interface BankInfoService {

    /**
     * 创建银行信息
     *
     * @param bankName 银行名称
     * @param cardType 卡类型
     * @param createUser 创建人
     * @return BankInfo
     */
    BankInfo createBankInfo(BankName bankName, CardType cardType, String createUser);

    /**
     * ID查询银行信息
     *
     * @param bankInfoId ID
     * @return Optional
     */
    Optional<BankInfo> findById(Long bankInfoId);

    /**
     * 修改银行信息
     *
     * @param bankInfoId ID
     * @param bankName 银行名称
     * @param cardType 卡类型
     * @param modifyUser 修改人
     * @return BankInfo
     */
    BankInfo reviseInfo(Long bankInfoId, BankName bankName, CardType cardType, String modifyUser);
}
