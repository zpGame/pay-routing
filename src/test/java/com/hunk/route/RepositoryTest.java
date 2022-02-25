package com.hunk.route;

import com.hunk.route.domain.*;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @author hunk
 * @date 2022/2/21
 *     <p>
 */
public class RepositoryTest extends RouteMainTests {

    @Resource RouteRuleRepository ruleRepository;
    @Resource BankInfoRepository bankInfoRepository;

    @Test
    public void bankInfoSave() {
        BankInfo bankInfo = new BankInfo("中国工商银行", "ICBC", CardType.DebitCard);
        bankInfoRepository.save(bankInfo);
    }

    @Test
    public void RouteRuleSave() {
        Set<BankInfo> bankInfos = new HashSet<>();
        bankInfos.add(bankInfoRepository.findById(1L).get());
        RouteRule routeRule =
                new RouteRule(TradeType.payment, AccountType.personal, bankInfos, new Money(1));
        ruleRepository.save(routeRule);
    }
}
