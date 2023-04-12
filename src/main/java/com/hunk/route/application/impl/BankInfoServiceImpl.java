package com.hunk.route.application.impl;

import com.hunk.route.application.BankInfoService;
import com.hunk.route.domain.event.BankInfoEvent;
import com.hunk.route.domain.event.ResultWithDomainEvents;
import com.hunk.route.domain.model.CreateInfo;
import com.hunk.route.domain.model.bank.BankInfo;
import com.hunk.route.domain.model.bank.BankInfoNotFoundException;
import com.hunk.route.domain.model.bank.BankInfoRepository;
import com.hunk.route.domain.model.bank.BankName;
import com.hunk.route.domain.model.em.CardType;
import com.hunk.route.infrastructure.messaging.event.CustomEventBus;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author hunk
 * @date 2022/2/25
 *     <p>
 */
public class BankInfoServiceImpl implements BankInfoService {

    private final BankInfoRepository bankInfoRepository;
    private final CustomEventBus customEventBus;

    public BankInfoServiceImpl(
            BankInfoRepository bankInfoRepository, CustomEventBus customEventBus) {
        this.bankInfoRepository = bankInfoRepository;
        this.customEventBus = customEventBus;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BankInfo createBankInfo(BankName bankName, CardType cardType, String createUser) {
        CreateInfo createInfo = CreateInfo.createInfo(createUser);
        ResultWithDomainEvents<BankInfo, BankInfoEvent> domainEvents =
                BankInfo.createBankInfo(bankName, cardType, createInfo);
        BankInfo bankInfo = domainEvents.result;
        bankInfoRepository.save(bankInfo);
        customEventBus.publish(domainEvents.event);
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
