package com.hunk.route.domain;

import java.util.List;

/**
 * @author hunk
 * @date 2022/2/17
 * <p>商户关联路由
 */
public class MerchantAssociateRoute {

    /**
     * 商户号、商户名称
     */
    private String merchantNo, merchantName;

    /**
     * 路由规则 1:N
     */
    private List<Route> routes;


}
