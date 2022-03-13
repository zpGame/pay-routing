package com.hunk.route.interfaces.web;

import com.hunk.route.application.RouteService;
import com.hunk.route.domain.*;
import com.hunk.route.interfaces.facade.dto.RouteChannelDTO;
import com.hunk.route.interfaces.facade.dto.RouteInfoDTO;
import com.hunk.route.interfaces.facade.internal.assembler.RouteChannelAssembler;
import com.hunk.route.interfaces.facade.page.PageBean;
import com.hunk.route.interfaces.facade.page.PageUtils;
import com.hunk.route.interfaces.web.command.ObtainRouteCommand;
import com.hunk.route.interfaces.web.command.RouteCreateCommand;
import com.hunk.route.interfaces.web.command.RouteReviseCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author hunk
 * @date 2022/2/27
 *     <p>
 */
@Api(tags = "获取路由模块")
@Controller
@RequestMapping("/route")
public class RouteController {

    private final RouteService routeService;
    private final RouteRepository routeRepository;

    public RouteController(RouteService routeService, RouteRepository routeRepository) {
        this.routeService = routeService;
        this.routeRepository = routeRepository;
    }

    @GetMapping()
    public String index() {
        return "index";
    }

    @PostMapping("/obtainRoute.do")
    @ApiOperation("获取路由信息")
    public RouteInfoDTO obtainRoute(ObtainRouteCommand command){
        return null;
    }

    @GetMapping("/list.do")
    public String list(Model model, HttpServletRequest request) {
        PageBean pageBean = new PageBean();
        pageBean.setRequest(request);
        Page<RouteChannel> all =
                routeRepository.findAll(PageRequest.of(pageBean.getPage() - 1, pageBean.getRows()));
        List<RouteChannelDTO> infoDtoS =
                all.getContent().stream()
                        .map(RouteChannelAssembler::toDto)
                        .collect(Collectors.toList());
        pageBean.setTotal(all.getTotalElements() + "");
        model.addAttribute("routeChannels", infoDtoS);
        model.addAttribute("pageCode", PageUtils.createPageCode(pageBean));
        return "route/list";
    }

    Function<String, ChannelE> channelE =
            channelE ->
                    ChannelE.getEnum(channelE)
                            .orElseThrow(() -> new NoSuchElementException(channelE));

    Function<String, ServiceE> serviceE =
            serviceE ->
                    ServiceE.getEnum(serviceE)
                            .orElseThrow(() -> new NoSuchElementException(serviceE));

    @PostMapping("/add.do")
    public String add(RouteCreateCommand command) {
        routeService.createRoute(
                new PaymentChannel(
                        channelE.apply(command.getChannelE()),
                        serviceE.apply(command.getServiceE())),
                command.getRuleId(),
                command.getPriority(),
                new EffectiveTime(command.getBeginDate(), command.getEndDate()),
                command.getCreateUser());
        return "redirect:/route/list.do";
    }

    @PostMapping("/edit.do")
    public String edit(RouteReviseCommand command) {
        routeService.reviseInfo(
                command.getOriId(),
                new PaymentChannel(
                        channelE.apply(command.getAlterChannelE()),
                        serviceE.apply(command.getAlterServiceE())),
                command.getAlterRuleId(),
                command.getAlterPriority(),
                new EffectiveTime(command.getAlterBeginDate(), command.getAlterBndDate()),
                command.getModifyUser());
        return "redirect:/route/list.do";
    }

    @PostMapping("/delete.do")
    public String delete(Long id) {
        routeRepository.deleteById(id);
        return "redirect:/route/list.do";
    }

    @PostMapping("/changeUpHold.do")
    public String changeUpHold(Long id, Integer upHold) {
        routeService.changeUpHold(id, upHold, "system");
        return "redirect:/route/list.do";
    }
}
