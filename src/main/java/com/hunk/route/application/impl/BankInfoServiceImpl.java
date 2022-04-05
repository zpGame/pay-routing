package com.hunk.route.application.impl;

import com.hunk.route.application.BankInfoService;
import com.hunk.route.domain.*;
import com.hunk.route.domain.event.BankInfoEvent;
import com.hunk.route.domain.event.ResultWithDomainEvents;
import com.hunk.route.infrastructure.messaging.event.injvm.DefaultCustomEventBus;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author hunk
 * @date 2022/2/25
 *     <p>
 */
public class BankInfoServiceImpl implements BankInfoService {

    private final BankInfoRepository bankInfoRepository;
    private final DefaultCustomEventBus defaultCustomEventBus;

    public BankInfoServiceImpl(
            BankInfoRepository bankInfoRepository, DefaultCustomEventBus defaultCustomEventBus) {
        this.bankInfoRepository = bankInfoRepository;
        this.defaultCustomEventBus = defaultCustomEventBus;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BankInfo createBankInfo(BankName bankName, CardType cardType, String createUser) {
        CreateInfo createInfo = CreateInfo.createInfo(createUser);
        ResultWithDomainEvents<BankInfo, BankInfoEvent> domainEvents =
                BankInfo.createBankInfo(bankName, cardType, createInfo);
        BankInfo bankInfo = domainEvents.result;
        bankInfoRepository.save(bankInfo);
        defaultCustomEventBus.publish(domainEvents.events);
        return bankInfo;
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
