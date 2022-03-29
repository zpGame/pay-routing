package com.hunk.route.interfaces.web;

import com.hunk.route.application.RouteRuleService;
import com.hunk.route.domain.*;
import com.hunk.route.interfaces.facade.dto.RuleInfoDTO;
import com.hunk.route.interfaces.facade.internal.assembler.RuleAssembler;
import com.hunk.route.interfaces.facade.page.PageBean;
import com.hunk.route.interfaces.facade.page.PageUtils;
import com.hunk.route.interfaces.web.command.RuleCreateCommand;
import com.hunk.route.interfaces.web.command.RuleReviseCommand;
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
 * @date 2022/3/9
 *     <p>
 */
@Controller
@RequestMapping("/rule")
public class RouteRuleController {

    @Resource
    private RouteRuleService routeRuleService;

    @Resource
    private RouteRuleRepository routeRuleRepository;

    @GetMapping("/list.do")
    public String list(Model model, HttpServletRequest request) {
        PageBean pageBean = new PageBean();
        pageBean.setRequest(request);
        Page<RouteRule> all =
                routeRuleRepository.findAll(
                        PageRequest.of(pageBean.getPage() - 1, pageBean.getRows()));
        List<RuleInfoDTO> infoDtoS =
                all.getContent().stream().map(RuleAssembler::toDto).collect(Collectors.toList());
        pageBean.setTotal(all.getTotalElements() + "");
        model.addAttribute("routeRules", infoDtoS);
        model.addAttribute("pageCode", PageUtils.createPageCode(pageBean));
        return "rule/list";
    }

    @PostMapping("/add.do")
    public String add(RuleCreateCommand command) {
        routeRuleService.createRouteRule(
                TradeType.valueOf(command.getTradeType()),
                AccountType.valueOf(command.getAccountType()),
                command.getBankInfoIds(),
                new Money(command.getMoney()),
                command.getCreateUser());
        return "redirect:/rule/list.do";
    }

    @PostMapping("/edit.do")
    public String edit(RuleReviseCommand command) {
        routeRuleService.reviseInfo(
                command.getOriId(),
                TradeType.valueOf(command.getAlterTradeType()),
                AccountType.valueOf(command.getAlterAccountType()),
                command.getAlterBankInfoIds(),
                new Money(command.getAlterMoney()),
                command.getModifyUser());
        return "redirect:/rule/list.do";
    }

    @PostMapping("/delete.do")
    public String delete(Long id) {
        routeRuleRepository.deleteById(id);
        return "redirect:/rule/list.do";
    }
}
