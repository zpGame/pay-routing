package com.hunk.route.interfaces.web;

import com.hunk.route.application.MerchantService;
import com.hunk.route.domain.MerchantRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
