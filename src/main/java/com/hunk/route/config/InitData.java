package com.hunk.route.config;

import com.hunk.route.domain.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hunk
 * @date 2022/3/1
 *     <p> 测试需要
 */
public class InitData implements ApplicationRunner {

    private final BankInfoRepository bankInfoRepository;

    public InitData(BankInfoRepository bankInfoRepository) {
        this.bankInfoRepository = bankInfoRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<BankInfo> bankInfos = new ArrayList<>();
        bankInfos.add(BankInfo.createBankInfo(new BankName("中国工商银行","ICBC"), CardType.DebitCard, CreateInfo.createInfo("system")));
        bankInfos.add(BankInfo.createBankInfo(new BankName("中国建设银行","CBC"), CardType.DebitCard, CreateInfo.createInfo("system")));
        bankInfos.add(BankInfo.createBankInfo(new BankName("中国银行","BC"), CardType.DebitCard, CreateInfo.createInfo("system")));
        bankInfos.add(BankInfo.createBankInfo(new BankName("中国农业银行","ABC"), CardType.DebitCard, CreateInfo.createInfo("system")));
        bankInfos.add(BankInfo.createBankInfo(new BankName("民生银行","CMSB"), CardType.DebitCard, CreateInfo.createInfo("system")));
        bankInfos.add(BankInfo.createBankInfo(new BankName("招商银行","CMBC"), CardType.DebitCard, CreateInfo.createInfo("system")));
        bankInfos.add(BankInfo.createBankInfo(new BankName("兴业银行","CIB"), CardType.DebitCard, CreateInfo.createInfo("system")));
        bankInfos.add(BankInfo.createBankInfo(new BankName("国家开发银行","CDB"), CardType.DebitCard, CreateInfo.createInfo("system")));
        bankInfoRepository.saveAll(bankInfos);
    }
}
