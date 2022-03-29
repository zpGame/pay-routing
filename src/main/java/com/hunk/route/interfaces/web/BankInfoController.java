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
import com.hunk.route.interfaces.web.command.BankCreatCommand;
import com.hunk.route.interfaces.web.command.BankReviseCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
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

    @Resource
    private BankInfoService bankInfoService;

    @Resource
    private BankInfoRepository bankInfoRepository;


    @GetMapping("/list.do")
    public String list(Model model, HttpServletRequest request) {
        PageBean pageBean = new PageBean();
        pageBean.setRequest(request);
        Page<BankInfo> all =
                bankInfoRepository.findAll(
                        PageRequest.of(pageBean.getPage() - 1, pageBean.getRows()));
        List<BankInfoDTO> infoDtoS =
                all.getContent().stream().map(BanInfoAssembler::toDto).collect(Collectors.toList());
        pageBean.setTotal(all.getTotalElements() + "");
        model.addAttribute("bankInfos", infoDtoS);
        model.addAttribute("pageCode", PageUtils.createPageCode(pageBean));
        return "bank/list";
    }

    @PostMapping("/add.do")
    public String add(BankCreatCommand command) {
        bankInfoService.createBankInfo(
                new BankName(command.getBankName(), command.getBankShortName()),
                CardType.valueOf(command.getCardType()),
                command.getCreateUser());
        return "redirect:/bank/list.do";
    }

    @PostMapping("/edit.do")
    public String edit(BankReviseCommand command) {
        bankInfoService.reviseInfo(
                command.getOriId(),
                new BankName(command.getAlterBankName(), command.getAlterBankShortName()),
                CardType.valueOf(command.getAlterCardType()),
                command.getModifyUser());
        return "redirect:/bank/list.do";
    }

    @PostMapping("/delete.do")
    public String delete(Long id) {
        bankInfoRepository.deleteById(id);
        return "redirect:/bank/list.do";
    }
}
