package com.hunk.route.application.impl;

import com.hunk.route.application.BankInfoService;
import com.hunk.route.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author hunk
 * @date 2022/2/25
 *     <p>
 */
public class BankInfoServiceImpl implements BankInfoService {

    private final BankInfoRepository bankInfoRepository;

    public BankInfoServiceImpl(BankInfoRepository bankInfoRepository) {
        this.bankInfoRepository = bankInfoRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BankInfo createBankInfo(BankName bankName, CardType cardType, String createUser) {
        CreateInfo createInfo = CreateInfo.createInfo(createUser);
        BankInfo bankInfo = BankInfo.createBankInfo(bankName, cardType, createInfo);
        return bankInfoRepository.save(bankInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<BankInfo> findById(Long bankInfoId) {
        return bankInfoRepository.findById(bankInfoId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BankInfo reviseInfo(
            Long bankInfoId, BankName bankName, CardType cardType, String modifyUser) {
        BankInfo bankInfo =
                bankInfoRepository
                        .findById(bankInfoId)
                        .orElseThrow(() -> new BankInfoNotFoundException(bankInfoId));
        CreateInfo createInfo = bankInfo.getCreateInfo().reviseInfo(modifyUser);
        BankInfo changeBankInfo =
                bankInfo.changeBankName(bankName)
                        .changeCardType(cardType)
                        .changeCreateInfo(createInfo);
        return bankInfoRepository.save(changeBankInfo);
    }

}
