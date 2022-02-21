package com.hunk.route.domain;

import javax.persistence.*;
import java.util.List;

/**
 * @author hunk
 * @date 2022/2/17
 *     <p>商户关联路由
 */
@Entity
@Table(name = "merchant_route")
@Access(AccessType.FIELD)
public class MerchantAssociateRoute {

    @Id
    @GeneratedValue
    private Long id;

    /** 商户号 */
    private String merchantNo;

    /** 商户名称 */
    private String merchantName;

    /** 路由规则 1:N */
    @OneToMany
    private List<Route> routes;
}
