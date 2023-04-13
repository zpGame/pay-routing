package com.hunk.route.interfaces.web;

import com.hunk.route.application.BankInfoService;
import com.hunk.route.domain.model.bank.BankInfo;
import com.hunk.route.domain.model.bank.BankInfoRepository;
import com.hunk.route.domain.model.bank.BankName;
import com.hunk.route.domain.model.em.CardType;
import com.hunk.route.interfaces.facade.dto.BankInfoDTO;
import com.hunk.route.interfaces.facade.internal.assembler.BanInfoAssembler;
import com.hunk.route.interfaces.web.command.BankCommand;
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
import java.util.stream.Collectors;

/**
 * @author hunk
 * @date 2022/3/1
 *     <p>
 */
@Controller
@RequestMapping("/bank")
public class BankInfoController extends BaseController {

    @Resource private BankInfoService bankInfoService;

    @Resource private BankInfoRepository bankInfoRepository;

    @GetMapping()
    public String index() {
        return "bankInfo";
    }

    @PostMapping("/list.do")
    public void list(
            HttpServletResponse response,
            @RequestParam(value = "rows") Integer size,
            @RequestParam(value = "page") Integer page)
            throws IOException {
        final PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<BankInfo> all = bankInfoRepository.findAll(pageRequest);
        List<BankInfoDTO> infoDtoS =
                all.getContent().stream().map(BanInfoAssembler::toDto).collect(Collectors.toList());
        super.pageWrite(response, all.getTotalPages(), infoDtoS);
    }

    @PostMapping("/add.do")
    public void add(HttpServletResponse response, BankCommand command) throws IOException {
        bankInfoService.createBankInfo(
                new BankName(command.getBankName(), command.getBankShortName()),
                CardType.valueOf(command.getCardType()),
                command.getCreateUser());
        super.write(response);
    }

    @PostMapping("/edit.do")
    public void edit(HttpServletResponse response, BankCommand command) throws IOException {
        bankInfoService.reviseInfo(
                command.getId(),
                new BankName(command.getBankName(), command.getBankShortName()),
                CardType.valueOf(command.getCardType()),
                command.getModifyUser());
        super.write(response);
    }

    @PostMapping("/delete.do")
    public void delete(HttpServletResponse response, Long id) throws IOException {
        bankInfoRepository.deleteById(id);
        super.write(response);
    }
}
