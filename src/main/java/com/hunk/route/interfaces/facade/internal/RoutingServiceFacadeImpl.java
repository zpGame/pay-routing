package com.hunk.route.interfaces.facade.internal;

import com.hunk.route.application.MerchantService;
import com.hunk.route.domain.*;
import com.hunk.route.interfaces.facade.RoutingServiceFacade;
import com.hunk.route.interfaces.facade.dto.RouteInfoDTO;
import com.hunk.route.interfaces.facade.internal.assembler.RouteInfoAssembler;
import com.hunk.route.interfaces.web.ObtainRouteCommand;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Set;

/**
 * @author hunk
 * @date 2022/3/3
 *     <p>todo 临时代码，后期优化
 */
public class RoutingServiceFacadeImpl implements RoutingServiceFacade {

    private final MerchantService merchantService;

    public RoutingServiceFacadeImpl(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @Override
    public RouteInfoDTO obtainRoute(ObtainRouteCommand command) {
        MerchantRoute merchantRoute = merchantService.findByMerchantNo(command.getMerchantNo());
        Set<RouteChannel> routeChannels = merchantRoute.obtainRoutes();
        RouteChannel routeChannel = new RouteChannel();
        RouteRule routeRule = new RouteRule();
        BankInfo bankInfo = new BankInfo();
        boolean isResult = false;
        for (RouteChannel route : routeChannels) {
            routeChannel = route;
            routeRule = route.getRouteRule();
            if (!validRule(routeRule, command)) continue;
            Set<BankInfo> bankInfos = routeRule.getBankInfos();
            if (CollectionUtils.isEmpty(bankInfos)) {
                isResult = true;
                break;
            }
            bankInfo =
                    bankInfos.stream()
                            .filter(data -> data.validBankShortName(command.getBankShortName()))
                            .findFirst()
                            .orElse(null);
            if (ObjectUtils.isEmpty(bankInfos)) continue;
            if (!bankInfo.validCardType(CardType.valueOf(command.getCardType()))) continue;
            isResult = true;
        }
        return RouteInfoAssembler.toDto(merchantRoute, routeChannel, routeRule, bankInfo, isResult);
    }

    private boolean validRule(RouteRule routeRule, ObtainRouteCommand command) {
        return routeRule.validAccountType(AccountType.valueOf(command.getAccountType()))
                && routeRule.validTradeType(TradeType.valueOf(command.getTradeType()))
                && routeRule.validMoney(new Money(command.getMoney()));
    }
}
