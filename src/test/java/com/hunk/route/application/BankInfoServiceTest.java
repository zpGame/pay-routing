package com.hunk.route.application;

import com.hunk.route.ApplicationTests;
import com.hunk.route.domain.BankInfo;
import com.hunk.route.domain.BankName;
import com.hunk.route.domain.CardType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @author hunk
 * @date 2022/2/28
 *     <p>
 */
public class BankInfoServiceTest extends ApplicationTests {

    @Autowired private BankInfoService bankInfoService;

    @Test
    public void createBankInfo() {
        BankName bankName = new BankName("中国工商银行", "ICBC");
        CardType cardType = CardType.DebitCard;
        BankInfo bankInfo = bankInfoService.createBankInfo(bankName, cardType, "system");
        System.out.println(bankInfo);
    }

    @Test
    public void findById() {
        System.out.println(bankInfoService.findById(1L));
    }

    @Test
    public void reviseInfo() {
        BankName bankName = new BankName("中国建设银行", "CBC");
        CardType cardType = CardType.CreditCard;
        System.out.println(bankInfoService.reviseInfo(1L, bankName, cardType, "admin"));
    }

    @Test
    public void findAll(){
        PageRequest pageRequest = PageRequest.of(0,5);
        Page<BankInfo> all = bankInfoService.findAll(pageRequest);
        int totalPages = all.getTotalPages();
        List<BankInfo> content = all.getContent();
        System.out.println(totalPages);
        System.out.println(content);
    }
}
