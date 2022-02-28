package com.hunk.route.domain;

import javax.persistence.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
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

    @Column(name = "merchant_no", length = 32, unique = true)
    private String merchantNo;

    @Column(name = "merchant_name", length = 56)
    private String merchantName;

    @ManyToMany
    @JoinTable(
            name = "merchant_junction_route",
            joinColumns = {@JoinColumn(name = "sys_merchant_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "sys_route_id", referencedColumnName = "id")},
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT),
            inverseForeignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Set<RouteChannel> routeChannels = new HashSet<>();

    @Embedded private CreateInfo createInfo;

    public static MerchantRoute createMerchant(
            String merchantNo,
            String merchantName,
            Set<RouteChannel> routeChannels,
            CreateInfo createInfo) {
        return new MerchantRoute(merchantNo, merchantName, routeChannels, createInfo);
    }

    public MerchantRoute() {}

    public MerchantRoute(
            String merchantNo,
            String merchantName,
            Set<RouteChannel> routeChannels,
            CreateInfo createInfo) {
        this.merchantNo = merchantNo;
        this.merchantName = merchantName;
        this.routeChannels = routeChannels;
        this.createInfo = createInfo;
    }

    public CreateInfo getCreateInfo() {
        return createInfo;
    }

    public MerchantRoute changeMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
        return this;
    }

    public MerchantRoute changeMerchantName(String merchantName) {
        this.merchantName = merchantName;
        return this;
    }

    public MerchantRoute changeRouteChannels(Set<RouteChannel> routeChannels) {
        this.routeChannels = routeChannels;
        return this;
    }

    public MerchantRoute changeCreateInfo(CreateInfo createInfo) {
        this.createInfo = createInfo;
        return this;
    }

    public Set<RouteChannel> obtainRoutes() {
        return routeChannels.stream()
                .filter(RouteChannel::isUpHold)
                .filter(route -> route.getEffectiveTime().validTime())
                .sorted(Comparator.comparing(RouteChannel::getPriority))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
