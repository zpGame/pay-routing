package com.hunk.route.interfaces.web.command;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @author hunk
 * @date 2022/3/11
 *     <p>
 */
@Data
public class MerchantCommand {
    private Long id;
    private String merchantNo;
    private String merchantName;
    private Set<String> routeIds = new HashSet<>();
    private String createUser;
    private String modifyUser;
}
