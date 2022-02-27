package com.hunk.route.domain;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author hunk
 * @date 2022/2/17
 *     <p>商户关联路由
 */
@Entity
@Table(name = "merchant_route")
public class MerchantRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "merchant_no", unique = true)
    private String merchantNo;

    @Column(name = "merchant_name")
    private String merchantName;

    @ManyToMany
    @JoinTable(
            name = "merchant_junction_route",
            joinColumns = {@JoinColumn(name = "sys_merchant_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "sys_route_id", referencedColumnName = "id")},
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT),
            inverseForeignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Set<RouteChannel> routeChannels = new HashSet<>();

    public static MerchantRoute createMerchant(
            String merchantNo, String merchantName, Set<RouteChannel> routeChannels) {
        return new MerchantRoute(merchantNo, merchantName, routeChannels);
    }

    public MerchantRoute() {}

    public MerchantRoute(String merchantNo, String merchantName, Set<RouteChannel> routeChannels) {
        this.merchantNo = merchantNo;
        this.merchantName = merchantName;
        this.routeChannels = routeChannels;
    }

    public Set<RouteChannel> obtainRoutes() {
        return routeChannels.stream()
                .filter(RouteChannel::isUpHold)
                .filter(RouteChannel::validTime)
                .sorted(Comparator.comparing(RouteChannel::getPriority))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
