package com.hunk.route.interfaces.facade.internal;

import com.hunk.route.application.MerchantService;
import com.hunk.route.domain.*;
import com.hunk.route.interfaces.facade.RoutingServiceFacade;
import com.hunk.route.interfaces.facade.dto.RouteInfoDTO;
import com.hunk.route.interfaces.facade.internal.assembler.RouteInfoAssembler;
import com.hunk.route.interfaces.web.ObtainRouteCommand;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author hunk
 * @date 2022/3/3
 *     <p>
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
        RouteRule routeRule = new RouteRule();
        BankInfo bankInfo = new BankInfo();
        boolean isResult = false;
        for (RouteChannel routeChannel : routeChannels) {
            routeRule = routeChannel.getRouteRule();
            if (!validRule(routeRule, command)) continue;
            Set<BankInfo> bankInfos = routeRule.getBankInfos();
            if (!CollectionUtils.isEmpty(bankInfos)) {
                bankInfo =
                        bankInfos.stream()
                                .collect(
                                        Collectors.toMap(
                                                data -> data.getBankName().getBankShortName(),
                                                Function.identity()))
                                .get(command.getBankShortName());
            }
        }
        return RouteInfoAssembler.toDto(merchantRoute, routeRule, bankInfo, isResult);
    }

    private boolean validRule(RouteRule routeRule, ObtainRouteCommand command) {
        return routeRule.validAccountType(AccountType.valueOf(command.getAccountType()))
                && routeRule.validTradeType(TradeType.valueOf(command.getTradeType()))
                && routeRule.validMoney(new Money(command.getMoney()));
    }
}
