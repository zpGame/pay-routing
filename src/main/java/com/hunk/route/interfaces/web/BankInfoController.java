package com.hunk.route.interfaces.web;

import com.hunk.route.application.BankInfoService;
import com.hunk.route.domain.BankInfo;
import com.hunk.route.interfaces.facade.dto.BankInfoDTO;
import com.hunk.route.interfaces.facade.internal.assembler.BanInfoAssembler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hunk
 * @date 2022/3/1
 *     <p>
 */
@Controller
@RequestMapping("/bank")
public class BankInfoController {

    private final BankInfoService bankInfoService;

    public BankInfoController(BankInfoService bankInfoService) {
        this.bankInfoService = bankInfoService;
    }

    @RequestMapping("/list.do")
    public String list(Model model) {
        Page<BankInfo> all = bankInfoService.findAll(PageRequest.of(1, 10));
        List<BankInfoDTO> infoDtoS =
                all.getContent().stream().map(BanInfoAssembler::toDTO).collect(Collectors.toList());
        model.addAttribute("bankInfos", infoDtoS);
        return "bank/list";
    }
}
