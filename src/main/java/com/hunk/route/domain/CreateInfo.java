package com.hunk.route.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

/**
 * @author hunk
 * @date 2022/2/27
 *     <p>流水创建信息
 */
@Embeddable
public class CreateInfo {

    private String createUser;
    private String modifyUser;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;

    public CreateInfo() {}

    public CreateInfo(
            String createUser,
            String modifyUser,
            LocalDateTime createTime,
            LocalDateTime modifyTime) {
        this.createUser = createUser;
        this.modifyUser = modifyUser;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public static CreateInfo createInfo(String createUser) {
        return new CreateInfo(createUser, null, LocalDateTime.now(), null);
    }

    public CreateInfo reviseInfo(String modifyUser) {
        this.modifyUser = modifyUser;
        this.modifyTime = LocalDateTime.now();
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("createUser", createUser)
                .append("modifyUser", modifyUser)
                .append("createTime", createTime)
                .append("modifyTime", modifyTime)
                .toString();
    }
}
