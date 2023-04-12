package com.hunk.route.domain.model;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author hunk
 * @date 2022/3/9
 * <p>
 */
@Getter
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Version
    private Long version;

    @Embedded
    protected CreateInfo createInfo;

}
