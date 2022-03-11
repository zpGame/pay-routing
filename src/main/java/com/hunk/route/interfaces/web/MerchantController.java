package com.hunk.route.interfaces.web;

import com.hunk.route.application.MerchantService;
import com.hunk.route.domain.MerchantRepository;
import com.hunk.route.domain.MerchantRoute;
import com.hunk.route.interfaces.facade.dto.MerchantInfoDTO;
import com.hunk.route.interfaces.facade.internal.assembler.MerchantAssembler;
import com.hunk.route.interfaces.facade.page.PageBean;
import com.hunk.route.interfaces.facade.page.PageUtils;
import com.hunk.route.interfaces.web.command.MerchantCreateCommand;
import com.hunk.route.interfaces.web.command.MerchantReviseCommand;
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
 * @date 2022/3/10
 *     <p>
 */
@Controller
@RequestMapping("/merchant")
public class MerchantController {

    private final MerchantService merchantService;
    private final MerchantRepository merchantRepository;

    public MerchantController(
            MerchantService merchantService, MerchantRepository merchantRepository) {
        this.merchantService = merchantService;
        this.merchantRepository = merchantRepository;
    }

    @RequestMapping("/list.do")
    public String list(Model model, HttpServletRequest request) {
        PageBean pageBean = new PageBean();
        pageBean.setRequest(request);
        Page<MerchantRoute> all =
                merchantRepository.findAll(
                        PageRequest.of(pageBean.getPage() - 1, pageBean.getRows()));
        List<MerchantInfoDTO> infoDtoS =
                all.getContent().stream()
                        .map(MerchantAssembler::toDto)
                        .collect(Collectors.toList());
        pageBean.setTotal(all.getTotalElements() + "");
        model.addAttribute("routeChannels", infoDtoS);
        model.addAttribute("pageCode", PageUtils.createPageCode(pageBean));
        return "merchant/list";
    }

    @RequestMapping("/add.do")
    public String add(MerchantCreateCommand command) {
        merchantService.createMerchant(
                command.getMerchantNo(),
                command.getMerchantName(),
                command.getRouteIds(),
                command.getCreateUser());
        return "redirect:/merchant/list.do";
    }

    @RequestMapping("/edit.do")
    public String edit(MerchantReviseCommand command) {
        merchantService.reviseInfo(
                command.getOriId(),
                command.getAlterMerchantNo(),
                command.getAlterMerchantName(),
                command.getAlterRouteIds(),
                command.getModifyUser());
        return "redirect:/merchant/list.do";
    }

    @RequestMapping("/delete.do")
    public String delete(Long id) {
        merchantRepository.deleteById(id);
        return "redirect:/merchant/list.do";
    }
}
