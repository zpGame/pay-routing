package com.hunk.route.interfaces.web.command;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hunk
 * @date 2022/3/11
 *     <p>
 */
@Data
public class MerchantCreateCommand {
    private String merchantNo;
    private String merchantName;
    private List<Long> routeIds = new ArrayList<>();
    private String createUser;
}
