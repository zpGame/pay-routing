package com.hunk.route.interfaces.web.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author hunk
 * @date 2022/3/4
 *     <p>
 */
@Data
@ApiModel(description = "ObtainRouteCommand")
public class ObtainRouteCommand {

    @ApiModelProperty("merchantNo")
    private String merchantNo;

    @ApiModelProperty("tradeType")
    private String tradeType;

    @ApiModelProperty("accountType")
    private String accountType;

    @ApiModelProperty("bankShortName")
    private String bankShortName;

    @ApiModelProperty("cardType")
    private String cardType;

    @ApiModelProperty("money")
    private BigDecimal money;
}
