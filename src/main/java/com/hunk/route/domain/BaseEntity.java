package com.hunk.route.domain;

import lombok.Getter;

import javax.persistence.*;

/**
 * @author hunk
 * @date 2022/3/9
 * <p>
 */
@Getter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Version
    private Long version;

    @Embedded
    protected CreateInfo createInfo;

}
