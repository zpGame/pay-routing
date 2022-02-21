package com.hunk.route.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author hunk
 * @date 2022/2/17
 *     <p>银行信息
 */
@Data
@Embeddable
public class BankInfo {

    private String bankName;

    private String bankShortName;

    @Enumerated(EnumType.STRING)
    private CardType cardType;
}
