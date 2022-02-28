package com.hunk.route.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

/**
 * @author hunk
 * @date 2022/2/28
 *     <p>
 */
@Embeddable
public class EffectiveTime {

    /** 有效时间 */
    @Column(name = "begin_date", columnDefinition = "DATETIME DEFAULT NULL ")
    private LocalDateTime beginDate;

    @Column(name = "end_date", columnDefinition = "DATETIME DEFAULT NULL ")
    private LocalDateTime endDate;

    public EffectiveTime() {}

    public EffectiveTime(LocalDateTime beginDate, LocalDateTime endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public boolean validTime() {
        return LocalDateTime.now().isAfter(beginDate) && LocalDateTime.now().isBefore(endDate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("beginDate", beginDate)
                .append("endDate", endDate)
                .toString();
    }
}
