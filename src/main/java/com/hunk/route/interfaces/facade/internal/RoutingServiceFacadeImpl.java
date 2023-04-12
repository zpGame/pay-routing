package com.hunk.route.interfaces.facade.internal;

import com.hunk.route.application.ChannelCostService;
import com.hunk.route.application.MerchantService;
import com.hunk.route.domain.model.Money;
import com.hunk.route.domain.model.bank.BankInfo;
import com.hunk.route.domain.model.channel.ChannelCost;
import com.hunk.route.domain.model.channel.PaymentChannel;
import com.hunk.route.domain.model.em.AccountType;
import com.hunk.route.domain.model.em.TradeType;
import com.hunk.route.domain.model.merchant.MerchantRoute;
import com.hunk.route.domain.model.route.RouteChannel;
import com.hunk.route.domain.model.rule.RouteRule;
import com.hunk.route.interfaces.facade.RoutingServiceFacade;
import com.hunk.route.interfaces.facade.dto.RouteInfoDTO;
import com.hunk.route.interfaces.facade.internal.assembler.RouteInfoAssembler;
import com.hunk.route.interfaces.web.command.ObtainRouteCommand;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author hunk
 * @date 2022/3/3
 *     <p>
 */
public class RoutingServiceFacadeImpl implements RoutingServiceFacade {

    private final MerchantService merchantService;
    private final ChannelCostService channelCostService;

    public RoutingServiceFacadeImpl(
            MerchantService merchantService, ChannelCostService channelCostService) {
        this.merchantService = merchantService;
        this.channelCostService = channelCostService;
    }

    @Override
    public RouteInfoDTO obtainRoute(ObtainRouteCommand command) {
        MerchantRoute merchantRoute = merchantService.findByMerchantNo(command.getMerchantNo());
        final Map<PaymentChannel, RouteChannel> channelMap = channelMap(merchantRoute);
        final Set<ChannelCost> channelCosts = findChannelCost(channelMap.keySet());
        for (ChannelCost channelCost : channelCosts) {
            RouteChannel routeChannel = channelMap.get(channelCost.getPaymentChannel());
            RouteRule routeRule = routeChannel.getRouteRule();
            if (!validRule(routeRule, command)) continue;
            if (routeRule.bankInfosIsNull()) {
                return RouteInfoAssembler.toDto(merchantRoute, routeChannel, routeRule);
            }
            BankInfo bankInfo =
                    routeRule.obtainBankInfo(command.getBankShortName(), command.getCardType());
            if (null != bankInfo) {
                return RouteInfoAssembler.toDto(merchantRoute, routeChannel, routeRule, bankInfo);
            }
        }
        return new RouteInfoDTO();
    }

    private Map<PaymentChannel, RouteChannel> channelMap(MerchantRoute merchantRoute) {
        Set<RouteChannel> routeChannels = merchantRoute.obtainRoutes();
        return routeChannels.stream()
                .collect(Collectors.toMap(RouteChannel::getPaymentChannel, Function.identity()));
    }

    private Set<ChannelCost> findChannelCost(Set<PaymentChannel> paymentChannels) {
        final List<PaymentChannel> collect = new ArrayList<>(paymentChannels);
        return channelCostService.findByPaymentChannelIn(collect).stream()
                .sorted(Comparator.comparing(ChannelCost::getRate))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private boolean validRule(RouteRule routeRule, ObtainRouteCommand command) {
        return routeRule.validAccountType(AccountType.valueOf(command.getAccountType()))
                && routeRule.validTradeType(TradeType.valueOf(command.getTradeType()))
                && routeRule.validMoney(new Money(command.getMoney()));
    }
}
