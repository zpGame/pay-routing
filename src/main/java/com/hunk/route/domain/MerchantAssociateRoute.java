package com.hunk.route.domain;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hunk
 * @date 2022/2/17
 *     <p>商户关联路由
 */
@Entity
@Table(name = "merchant_route")
@Access(AccessType.FIELD)
public class MerchantAssociateRoute {

    @Id @GeneratedValue private Long id;

    /** 商户号 */
    private String merchantNo;

    /** 商户名称 */
    private String merchantName;

    /** 路由规则 1:N */
    @OneToMany private List<Route> routes;

    public List<Route> obtainRoutes() {
        return routes.stream()
                .filter(Route::isUpHold)
                .filter(Route::validTime)
                .sorted(Comparator.comparing(Route::getPriority))
                .collect(Collectors.toList());
    }
}
