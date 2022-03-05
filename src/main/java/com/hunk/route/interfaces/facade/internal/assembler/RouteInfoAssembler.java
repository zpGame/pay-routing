package com.hunk.route.interfaces.facade.internal.assembler;

import com.hunk.route.domain.BankInfo;
import com.hunk.route.domain.MerchantRoute;
import com.hunk.route.domain.RouteRule;
import com.hunk.route.interfaces.facade.dto.RouteInfoDTO;

/**
 * @author hunk
 * @date 2022/3/4
 *     <p>
 */
public class RouteInfoAssembler {

    public static RouteInfoDTO toDto(
            MerchantRoute merchantRoute, RouteRule routeRule, BankInfo bankInfo, boolean isResult) {
        return new RouteInfoDTO();
    }
}
