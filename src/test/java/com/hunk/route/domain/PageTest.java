package com.hunk.route.domain;

import com.hunk.route.domain.model.route.RouteConstants;
import com.hunk.route.infrastructure.cache.Query;
import org.junit.Test;

/**
 * @author Zhao Peng
 * @date 2022/4/16
 * <p>
 */
public class PageTest {

    @Test
    public void test(){
        Query.Param param = new Query.Param(2, 5, RouteConstants.BANK_INFO_KEY_);
        System.out.println(param.getKey());
        System.out.println(param.getStart());
        System.out.println(param.getEnd());
    }
}
