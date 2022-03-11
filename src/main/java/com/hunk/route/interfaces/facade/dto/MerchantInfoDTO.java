package com.hunk.route.interfaces.facade.dto;

import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author hunk
 * @date 2022/3/11
 *     <p>
 */
@Getter
public class MerchantInfoDTO implements Serializable {

    private final Long id;

    private final String merchantNo;

    private final String merchantName;

    private final List<RouteChannelDTO> routeChannels;

    public MerchantInfoDTO(
            Long id, String merchantNo, String merchantName, List<RouteChannelDTO> routeChannels) {
        this.id = id;
        this.merchantNo = merchantNo;
        this.merchantName = merchantName;
        this.routeChannels = routeChannels;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("merchantNo", merchantNo)
                .append("merchantName", merchantName)
                .append("routeChannels", routeChannels)
                .toString();
    }
}
