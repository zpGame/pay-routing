package com.hunk.route.interfaces.web;

import com.hunk.route.application.RouteService;
import com.hunk.route.domain.model.channel.PaymentChannel;
import com.hunk.route.domain.model.em.ChannelE;
import com.hunk.route.domain.model.em.ServiceE;
import com.hunk.route.domain.model.route.EffectiveTime;
import com.hunk.route.domain.model.route.RouteChannel;
import com.hunk.route.domain.model.route.RouteRepository;
import com.hunk.route.interfaces.facade.dto.RouteChannelDTO;
import com.hunk.route.interfaces.facade.dto.RouteInfoDTO;
import com.hunk.route.interfaces.facade.internal.assembler.RouteChannelAssembler;
import com.hunk.route.interfaces.web.command.ObtainRouteCommand;
import com.hunk.route.interfaces.web.command.RouteCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
public class RouteController extends BaseController {

    @Resource private RouteService routeService;

    @Resource private RouteRepository routeRepository;

    @GetMapping()
    public String index() {
        return "index";
    }

    @PostMapping("/obtainRoute.do")
    @ApiOperation("获取路由信息")
    public RouteInfoDTO obtainRoute(ObtainRouteCommand command) {
        return null;
    }

    @GetMapping("/list.do")
    public void list(
            HttpServletResponse response,
            @RequestParam(value = "rows") Integer size,
            @RequestParam(value = "page") Integer page)
            throws IOException {
        final PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<RouteChannel> all = routeRepository.findAll(pageRequest);
        List<RouteChannelDTO> infoDtoS =
                all.getContent().stream()
                        .map(RouteChannelAssembler::toDto)
                        .collect(Collectors.toList());
        super.pageWrite(response, all.getTotalElements(), infoDtoS);
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
    public void add(HttpServletResponse response, RouteCommand command) throws IOException {
        routeService.createRoute(
                new PaymentChannel(
                        channelE.apply(command.getChannelE()),
                        serviceE.apply(command.getServiceE())),
                command.getRuleId(),
                new EffectiveTime(command.getBeginDate(), command.getEndDate()),
                command.getCreateUser());
        super.write(response);
    }

    @PostMapping("/edit.do")
    public void edit(HttpServletResponse response, RouteCommand command) throws IOException {
        routeService.reviseInfo(
                command.getId(),
                new PaymentChannel(
                        channelE.apply(command.getChannelE()),
                        serviceE.apply(command.getServiceE())),
                Long.parseLong(command.getRuleId()),
                new EffectiveTime(command.getBeginDate(), command.getEndDate()),
                command.getModifyUser());
        super.write(response);
    }

    @PostMapping("/delete.do")
    public void delete(HttpServletResponse response, Long id) throws IOException {
        routeRepository.deleteById(id);
        super.write(response);
    }

    @PostMapping("/changeUpHold.do")
    public void changeUpHold(HttpServletResponse response, Long id, Integer upHold)
            throws IOException {
        routeService.changeUpHold(id, upHold, "system");
        super.write(response);
    }
}
