package com.hunk.route.interfaces.web;

import com.hunk.route.application.RouteRuleService;
import com.hunk.route.domain.model.Money;
import com.hunk.route.domain.model.em.AccountType;
import com.hunk.route.domain.model.em.TradeType;
import com.hunk.route.domain.model.rule.RouteRule;
import com.hunk.route.domain.model.rule.RouteRuleRepository;
import com.hunk.route.interfaces.facade.dto.RuleInfoDTO;
import com.hunk.route.interfaces.facade.internal.assembler.RuleAssembler;
import com.hunk.route.interfaces.web.command.RuleCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hunk
 * @date 2022/3/9
 *     <p>
 */
@Controller
@RequestMapping("/rule")
public class RouteRuleController extends BaseController {

    @Resource private RouteRuleService routeRuleService;

    @Resource private RouteRuleRepository routeRuleRepository;

    @GetMapping()
    public String index() {
        return "rule";
    }

    @GetMapping("/list.do")
    public void list(
            HttpServletResponse response,
            @RequestParam(value = "rows") Integer size,
            @RequestParam(value = "page") Integer page)
            throws IOException {
        final PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<RouteRule> all = routeRuleRepository.findAll(pageRequest);
        List<RuleInfoDTO> infoDtoS =
                all.getContent().stream().map(RuleAssembler::toDto).collect(Collectors.toList());
        super.pageWrite(response, all.getTotalPages(), infoDtoS);
    }

    @PostMapping("/add.do")
    public void add(HttpServletResponse response, RuleCommand command) throws IOException {
        routeRuleService.createRouteRule(
                TradeType.valueOf(command.getTradeType()),
                AccountType.valueOf(command.getAccountType()),
                command.getBankIds(),
                new Money(command.getMoney()),
                command.getCreateUser());
        super.write(response);
    }

    @PostMapping("/edit.do")
    public void edit(HttpServletResponse response, RuleCommand command) throws IOException {
        routeRuleService.reviseInfo(
                command.getId(),
                TradeType.valueOf(command.getTradeType()),
                AccountType.valueOf(command.getAccountType()),
                new ArrayList<>(),
                new Money(command.getMoney()),
                command.getModifyUser());
        super.write(response);
    }

    @PostMapping("/delete.do")
    public void delete(HttpServletResponse response, Long id) throws IOException {
        routeRuleRepository.deleteById(id);
        super.write(response);
    }
}
