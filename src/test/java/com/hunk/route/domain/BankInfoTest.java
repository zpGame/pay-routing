package com.hunk.route.domain;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * @author hunk
 * @date 2022/2/22
 *     <p>
 */
public class BankInfoTest {

    private BankInfo bankInfo;

    @Before
    public void createBean() {
        BankName bankName = new BankName("中国工商银行", "ICBC");
        bankInfo = new BankInfo(bankName, CardType.DebitCard, CreateInfo.createInfo("system"));
    }

    @Test
    public void validBankShortName() {
        String bankShortName = "ICBC";
        boolean valid = bankInfo.validBankShortName(bankShortName);
        Assert.isTrue(valid, "is false");
    }

    @Test
    public void validCardType() {
        boolean valid = bankInfo.validCardType(CardType.DebitCard);
        Assert.isTrue(valid, "is false");
    }

    @Test
    public void changeBankName() {
        BankInfo change = bankInfo.changeBankName(null);
        System.out.println(change);
    }

    @Test
    public void changeCardType() {
        BankInfo change = bankInfo.changeCardType(CardType.CreditCard);
        System.out.println(change);
    }
}
