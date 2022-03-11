package com.hunk.route.interfaces.facade.internal.assembler;

import com.hunk.route.domain.RouteRule;
import com.hunk.route.interfaces.facade.dto.BankInfoDTO;
import com.hunk.route.interfaces.facade.dto.RuleInfoDTO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hunk
 * @date 2022/3/9
 *     <p>
 */
public class RuleAssembler {

    public static RuleInfoDTO toDto(RouteRule info) {
        List<BankInfoDTO> infoDtoS =
                info.getBankInfos().stream()
                        .map(BanInfoAssembler::toDto)
                        .collect(Collectors.toList());
        return new RuleInfoDTO(
                info.getId(),
                info.getTradeType().name(),
                info.getAccountType().name(),
                infoDtoS,
                info.getMoney().asString());
    }
}
