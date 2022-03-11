package com.hunk.route.interfaces.facade.internal.assembler;

import com.hunk.route.domain.BankInfo;
import com.hunk.route.domain.BankName;
import com.hunk.route.interfaces.facade.dto.BankInfoDTO;

/**
 * @author hunk
 * @date 2022/3/3
 *     <p>
 */
public class BanInfoAssembler {
    public static BankInfoDTO toDto(BankInfo info) {
        BankName bankName = info.getBankName();
        return new BankInfoDTO(
                info.getId(),
                bankName.getBankName(),
                bankName.getBankShortName(),
                info.getCardType().name());
    }
}
