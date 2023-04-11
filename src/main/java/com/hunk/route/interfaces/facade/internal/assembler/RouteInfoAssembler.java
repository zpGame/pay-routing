package com.hunk.route.interfaces.facade.internal.assembler;

import com.hunk.route.domain.model.bank.BankInfo;
import com.hunk.route.domain.model.merchant.MerchantRoute;
import com.hunk.route.domain.RouteChannel;
import com.hunk.route.domain.RouteRule;
import com.hunk.route.interfaces.facade.dto.RouteInfoDTO;

/**
 * @author hunk
 * @date 2022/3/4
 *     <p>
 */
public class RouteInfoAssembler {

    public static RouteInfoDTO toDto(
            MerchantRoute merchantRoute, RouteChannel routeChannel, RouteRule routeRule) {
        return RouteInfoAssembler.toDto(merchantRoute, routeChannel, routeRule, new BankInfo());
    }

    public static RouteInfoDTO toDto(
            MerchantRoute merchantRoute,
            RouteChannel routeChannel,
            RouteRule routeRule,
            BankInfo bankInfo) {
        return new RouteInfoDTO(
                merchantRoute.getMerchantNo(),
                merchantRoute.getMerchantName(),
                routeChannel.getPaymentChannel().getRouteInterface(),
                routeChannel.getPaymentChannel().getRouteName(),
                routeRule.getTradeType().name(),
                routeRule.getAccountType().name(),
                routeRule.getMoney().asString(),
                bankInfo.getBankName().getBankName(),
                bankInfo.getBankName().getBankShortName(),
                bankInfo.getCardType().name());
    }
}
