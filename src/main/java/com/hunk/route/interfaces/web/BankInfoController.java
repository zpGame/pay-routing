package com.hunk.route.interfaces.web;

import com.hunk.route.application.BankInfoService;
import com.hunk.route.domain.BankInfo;
import com.hunk.route.domain.BankInfoRepository;
import com.hunk.route.domain.BankName;
import com.hunk.route.domain.CardType;
import com.hunk.route.interfaces.facade.dto.BankInfoDTO;
import com.hunk.route.interfaces.facade.internal.assembler.BanInfoAssembler;
import com.hunk.route.interfaces.facade.page.PageBean;
import com.hunk.route.interfaces.facade.page.PageUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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
    public String list(Model model, HttpServletRequest request) {
        PageBean pageBean = new PageBean();
        pageBean.setRequest(request);
        Page<BankInfo> all =
                bankInfoRepository.findAll(
                        PageRequest.of(pageBean.getPage() - 1, pageBean.getRows()));
        List<BankInfoDTO> infoDtoS =
                all.getContent().stream().map(BanInfoAssembler::toDTO).collect(Collectors.toList());
        pageBean.setTotal(all.getTotalElements() + "");
        model.addAttribute("bankInfos", infoDtoS);
        model.addAttribute("pageCode", PageUtils.createPageCode(pageBean));
        return "bank/list";
    }

    @RequestMapping("/add.do")
    public String add(BankCreatCommand command) {
        bankInfoService.createBankInfo(
                new BankName(command.getBankName(), command.getBankShortName()),
                CardType.valueOf(command.getCardType()),
                command.getCreateUser());
        return "redirect:/bank/list.do";
    }

    @RequestMapping("/edit.do")
    public String edit(BankReviseCommand command) {
        bankInfoService.reviseInfo(
                command.getOriId(),
                new BankName(command.getAlterBankName(), command.getAlterBankShortName()),
                CardType.valueOf(command.getAlterCardType()),
                command.getModifyUser());
        return "redirect:/bank/list.do";
    }

    @RequestMapping("/delete.do")
    public String delete(Long id) {
        bankInfoRepository.deleteById(id);
        return "redirect:/bank/list.do";
    }
}
