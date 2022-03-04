package com.hunk.route.interfaces.web;

import com.hunk.route.application.BankInfoService;
import com.hunk.route.domain.*;
import com.hunk.route.interfaces.facade.dto.BankInfoDTO;
import com.hunk.route.interfaces.facade.internal.assembler.BanInfoAssembler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    private final BankInfoRepository bankInfoRepository;

    public BankInfoController(
            BankInfoService bankInfoService, BankInfoRepository bankInfoRepository) {
        this.bankInfoService = bankInfoService;
        this.bankInfoRepository = bankInfoRepository;
    }

    @RequestMapping("/list.do")
    public String list(Model model) {
        Page<BankInfo> all = bankInfoRepository.findAll(PageRequest.of(0, 10));
        List<BankInfoDTO> infoDtoS =
                all.getContent().stream().map(BanInfoAssembler::toDTO).collect(Collectors.toList());
        model.addAttribute("bankInfos", infoDtoS);
        return "bank/list";
    }

    @RequestMapping(value = "/toAdd.do", method = RequestMethod.GET)
    public String toAdd() {
        return "bank/add";
    }

    @RequestMapping("/add.do")
    public String add(BankCreatCommand command) {
        bankInfoService.createBankInfo(
                new BankName(command.getBankName(), command.getBankShortName()),
                CardType.valueOf(command.getCardType()),
                command.getCreateUser());
        return "redirect:/bank/list.do";
    }

    @RequestMapping("/toEdit.do")
    public String toEdit(Model model, Long id) {
        BankInfo bankInfo =
                bankInfoService.findById(id).orElseThrow(() -> new BankInfoNotFoundException(id));
        model.addAttribute("bankInfo", BanInfoAssembler.toDTO(bankInfo));
        return "bank/edit";
    }

    @RequestMapping("/edit.do")
    public String edit(BankReviseCommand command) {
        bankInfoService.reviseInfo(
                command.getId(),
                new BankName(command.getBankName(), command.getBankShortName()),
                CardType.valueOf(command.getCardType()),
                command.getModifyUser());
        return "redirect:/bank/list.do";
    }

    @RequestMapping("/delete.do")
    public String delete(Long id) {
        bankInfoRepository.deleteById(id);
        return "redirect:/bank/list.do";
    }
}
