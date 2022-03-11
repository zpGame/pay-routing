package com.hunk.route.interfaces.web.command;

import lombok.Data;

import java.util.List;

/**
 * @author hunk
 * @date 2022/3/11
 *     <p>
 */
@Data
public class MerchantReviseCommand {

    private Long oriId;
    private String alterMerchantNo;
    private String alterMerchantName;
    private List<Long> alterRouteIds;
    private String modifyUser;
}
