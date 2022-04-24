package com.hunk.route.domain;

import com.hunk.route.domain.event.MerchantRouteEvent;
import com.hunk.route.domain.event.ResultWithDomainEvents;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author hunk
 * @date 2022/2/17
 *     <p>商户关联路由
 */
@Entity
@Getter
@Table(name = "merchant_route")
public class MerchantRoute extends BaseEntity {

    public static ResultWithDomainEvents<MerchantRoute, MerchantRouteEvent> createMerchant(
            String merchantNo,
            String merchantName,
            Set<RouteChannel> routeChannels,
            CreateInfo createInfo) {
        MerchantRoute merchantRoute =
                new MerchantRoute(merchantNo, merchantName, routeChannels, createInfo);
        Set<String> channelIds =
                routeChannels.stream().map(RouteChannel::getChannelId).collect(Collectors.toSet());
        MerchantRouteEvent merchantRouteEvent =
                new MerchantRouteEvent(
                        merchantRoute.getMerchantId(), merchantNo, merchantName, channelIds);
        return new ResultWithDomainEvents<>(merchantRoute, merchantRouteEvent);
    }

    @Column(name = "merchant_id", length = 32)
    private String merchantId;

    @Column(name = "merchant_no", length = 32, unique = true)
    private String merchantNo;

    @Column(name = "merchant_name", length = 56)
    private String merchantName;

    @ManyToMany
    @JoinTable(
            name = "merchant_link_route",
            joinColumns = {@JoinColumn(name = "merchant_id", referencedColumnName = "merchant_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "channel_id", referencedColumnName = "channel_id")
            },
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT),
            inverseForeignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Set<RouteChannel> routeChannels = new HashSet<>();

    public MerchantRoute() {}

    public MerchantRoute(
            String merchantNo,
            String merchantName,
            Set<RouteChannel> routeChannels,
            CreateInfo createInfo) {
        this.merchantId = MajorKey.getId();
        this.merchantNo = merchantNo;
        this.merchantName = merchantName;
        this.routeChannels = routeChannels;
        this.createInfo = createInfo;
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
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("createInfo", createInfo)
                .append("merchantId", merchantId)
                .append("merchantNo", merchantNo)
                .append("merchantName", merchantName)
                .append("routeChannels", routeChannels)
                .toString();
    }
}
