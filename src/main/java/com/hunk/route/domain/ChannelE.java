package com.hunk.route.domain;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author hunk
 * @date 2022/2/23
 *     <p>
 */
public enum ChannelE {

    /** 渠道枚举 */
    UNION("union", "银联");
    private final String channel, channelName;

    public String getChannel() {
        return channel;
    }

    public String getChannelName() {
        return channelName;
    }

    ChannelE(String channel, String channelName) {
        this.channel = channel;
        this.channelName = channelName;
    }

    public Optional<ChannelE> getEnum(String channel) {
        return StringUtils.isBlank(channel)
                ? Optional.empty()
                : Arrays.stream(ChannelE.values())
                        .filter(data -> channel.equals(data.getChannel()))
                        .findFirst();
    }
}
