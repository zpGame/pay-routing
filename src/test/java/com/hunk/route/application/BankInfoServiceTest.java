package com.hunk.route.application;

import com.hunk.route.RouteMainTests;
import com.hunk.route.domain.BankInfo;
import com.hunk.route.domain.BankName;
import com.hunk.route.domain.CardType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author hunk
 * @date 2022/2/28
 *     <p>
 */
public class BankInfoServiceTest extends RouteMainTests {

    @Autowired private BankInfoService bankInfoService;

    @Test
    public void createBankInfo() {
        BankName bankName = new BankName("中国工商银行", "ICBC");
        CardType cardType = CardType.DebitCard;
        BankInfo bankInfo = bankInfoService.createBankInfo(bankName, cardType, "system");
        System.out.println(bankInfo);

        BankName bankName1 = new BankName("中国建设银行", "CBC");
        CardType cardType1 = CardType.DebitCard;
        BankInfo bankInfo1 = bankInfoService.createBankInfo(bankName1, cardType1, "system");
        System.out.println(bankInfo1);
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
}
