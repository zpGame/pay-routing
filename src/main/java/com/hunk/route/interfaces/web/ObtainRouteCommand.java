package com.hunk.route.interfaces.web;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author hunk
 * @date 2022/3/4
 *     <p>
 */
@Data
public class ObtainRouteCommand {

    private String merchantNo, tradeType, accountType, bankShortName, cardType;

    private BigDecimal money;
}
