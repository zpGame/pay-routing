package com.hunk.route.interfaces.web;

import com.hunk.route.application.MerchantService;
import com.hunk.route.domain.model.merchant.MerchantRepository;
import com.hunk.route.domain.model.merchant.MerchantRoute;
import com.hunk.route.interfaces.facade.dto.MerchantInfoDTO;
import com.hunk.route.interfaces.facade.internal.assembler.MerchantAssembler;
import com.hunk.route.interfaces.web.command.MerchantCommand;
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
 * @date 2022/3/10
 * <p>
 */
@Controller
@RequestMapping("/merchant")
public class MerchantController extends BaseController {

    @Resource
    private MerchantService merchantService;

    @Resource
    private MerchantRepository merchantRepository;

    @GetMapping("/list.do")
    public void list(
            HttpServletResponse response,
            @RequestParam(value = "rows") Integer size,
            @RequestParam(value = "page") Integer page)
            throws IOException {
        final PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<MerchantRoute> all = merchantRepository.findAll(pageRequest);
        List<MerchantInfoDTO> infoDtoS =
                all.getContent().stream()
                        .map(MerchantAssembler::toDto)
                        .collect(Collectors.toList());
        super.pageWrite(response, all.getTotalElements(), infoDtoS);
    }

    @PostMapping("/add.do")
    public void add(HttpServletResponse response, MerchantCommand command) throws IOException {
        merchantService.createMerchant(
                command.getMerchantNo(),
                command.getMerchantName(),
                command.getRouteIds(),
                command.getCreateUser());
        super.write(response);
    }

    @PostMapping("/edit.do")
    public void edit(HttpServletResponse response, MerchantCommand command) throws IOException {
        merchantService.reviseInfo(
                command.getId(),
                command.getMerchantNo(),
                command.getMerchantName(),
                new ArrayList<>(),
                command.getModifyUser());
        super.write(response);
    }

    @PostMapping("/delete.do")
    public void delete(HttpServletResponse response, Long id) throws IOException {
        merchantRepository.deleteById(id);
        super.write(response);
    }
}
