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

    private final String bankShortName = "ICBC";

    @Before
    public void createBean() {
        BankName bankName = new BankName("中国工商银行", "ICBC");
        bankInfo = new BankInfo(bankName, CardType.DebitCard, CreateInfo.createInfo("system"));
    }

    @Test
    public void valid() {
        boolean valid = bankInfo.valid(bankShortName, CardType.DebitCard);
        Assert.isTrue(valid, "is false");
    }

//    @Test
//    public void validBankShortName() {
//        boolean valid = bankInfo.validBankShortName(bankShortName);
//        Assert.isTrue(valid, "is false");
//    }

    @Test
    public void validCardType() {
        boolean valid = bankInfo.validCardType(CardType.DebitCard);
        Assert.isTrue(valid, "is false");
    }

//    @Test
//    public void changeBankName() {
//        BankInfo change = bankInfo.changeBankName("");
//        System.out.println(change);
//    }
//
//    @Test
//    public void changeBankShortName() {
//        BankInfo change = bankInfo.changeBankShortName(null);
//        System.out.println(change);
//    }

    @Test
    public void changeCardType() {
        BankInfo change = bankInfo.changeCardType(CardType.CreditCard);
        System.out.println(change);
    }
}
