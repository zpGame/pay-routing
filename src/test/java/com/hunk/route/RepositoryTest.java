package com.hunk.route;
import com.hunk.route.domain.CardType;
import com.hunk.route.domain.AccountType;
import com.hunk.route.domain.TradeType;
import com.hunk.route.domain.BankInfo;
import com.hunk.route.domain.Money;
import java.util.ArrayList;
import java.util.UUID;

import com.hunk.route.domain.RouteRule;
import com.hunk.route.domain.RouteRuleRepository;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author hunk
 * @date 2022/2/21
 * <p>
 */
public class RepositoryTest extends RouteMainTests{

    @Resource
    RouteRuleRepository ruleRepository;

    @Test
    public void save(){
        RouteRule routeRule = new RouteRule();
        routeRule.setId(0L);
        routeRule.setRuleId(UUID.randomUUID().toString());
        routeRule.setTradeType(TradeType.payment);
        routeRule.setAccountType(AccountType.personal);

        ArrayList<BankInfo> bankInfos = new ArrayList<>();

        BankInfo bankInfo = new BankInfo();
        bankInfo.setBankName("111");
        bankInfo.setBankShortName("222");
        bankInfo.setCardType(CardType.CreditCard);

        bankInfos.add(bankInfo);
        routeRule.setBankInfos(bankInfos);
        routeRule.setMoney(new Money(1));

        RouteRule save = ruleRepository.save(routeRule);

        System.out.println(ruleRepository.findAll());
    }

}
