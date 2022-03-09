package com.hunk.route.interfaces.facade.dto;

import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author hunk
 * @date 2022/3/4
 *     <p>
 */
@Getter
public class RouteInfoDTO implements Serializable {

    private String merchantNo;
    private String merchantName;
    private String routeInterface;
    private String routeName;
    private String tradeType;
    private String accountType;
    private String money;
    private String bankName;
    private String bankShortName;
    private String cardType;

    public RouteInfoDTO() {}

    public RouteInfoDTO(
            String merchantNo,
            String merchantName,
            String routeInterface,
            String routeName,
            String tradeType,
            String accountType,
            String money,
            String bankName,
            String bankShortName,
            String cardType) {
        this.merchantNo = merchantNo;
        this.merchantName = merchantName;
        this.routeInterface = routeInterface;
        this.routeName = routeName;
        this.tradeType = tradeType;
        this.accountType = accountType;
        this.money = money;
        this.bankName = bankName;
        this.bankShortName = bankShortName;
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("merchantNo", merchantNo)
                .append("merchantName", merchantName)
                .append("routeInterface", routeInterface)
                .append("routeName", routeName)
                .append("tradeType", tradeType)
                .append("accountType", accountType)
                .append("money", money)
                .append("bankName", bankName)
                .append("bankShortName", bankShortName)
                .append("cardType", cardType)
                .toString();
    }
}
