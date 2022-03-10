package com.hunk.route.interfaces.web;

import com.hunk.route.application.RouteService;
import com.hunk.route.domain.RouteChannel;
import com.hunk.route.domain.RouteRepository;
import com.hunk.route.interfaces.facade.dto.RouteChannelDTO;
import com.hunk.route.interfaces.facade.internal.assembler.RouteChannelAssembler;
import com.hunk.route.interfaces.facade.page.PageBean;
import com.hunk.route.interfaces.facade.page.PageUtils;
import com.hunk.route.interfaces.web.command.BankCreatCommand;
import com.hunk.route.interfaces.web.command.BankReviseCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hunk
 * @date 2022/2/27
 *     <p>
 */
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

    @RequestMapping("/list.do")
    public String list(Model model, HttpServletRequest request) {
        PageBean pageBean = new PageBean();
        pageBean.setRequest(request);
        Page<RouteChannel> all =
                routeRepository.findAll(
                        PageRequest.of(pageBean.getPage() - 1, pageBean.getRows()));
        List<RouteChannelDTO> infoDtoS =
                all.getContent().stream().map(RouteChannelAssembler::toDTO).collect(Collectors.toList());
        pageBean.setTotal(all.getTotalElements() + "");
        model.addAttribute("routeChannels", infoDtoS);
        model.addAttribute("pageCode", PageUtils.createPageCode(pageBean));
        return "route/list";
    }

    @RequestMapping("/add.do")
    public String add(BankCreatCommand command) {
//        routeService.createRoute();
        return "redirect:/route/list.do";
    }

    @RequestMapping("/edit.do")
    public String edit(BankReviseCommand command) {
//        routeService.reviseInfo();
        return "redirect:/route/list.do";
    }

    @RequestMapping("/delete.do")
    public String delete(Long id) {
        routeRepository.deleteById(id);
        return "redirect:/route/list.do";
    }
}
