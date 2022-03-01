package com.hunk.route.domain;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author hunk
 * @date 2022/2/23
 *     <p>
 */
public enum ServiceE {

    /** 业务枚举 */
    WITHHOLD("withHold", "代扣");
    private final String service, serviceName;

    public String getService() {
        return service;
    }

    public String getServiceName() {
        return serviceName;
    }

    ServiceE(String service, String serviceName) {
        this.service = service;
        this.serviceName = serviceName;
    }

    public Optional<ServiceE> getEnum(String service) {
        return StringUtils.isBlank(service)
                ? Optional.empty()
                : Arrays.stream(ServiceE.values())
                        .filter(data -> service.equals(data.getService()))
                        .findFirst();
    }
}
