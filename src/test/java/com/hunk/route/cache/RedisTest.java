package com.hunk.route.cache;

import com.alibaba.fastjson.JSONObject;
import com.hunk.route.ApplicationTests;
import com.hunk.route.domain.BankName;
import com.hunk.route.domain.CardType;
import com.hunk.route.domain.RouteConstants;
import com.hunk.route.domain.event.BankInfoEvent;
import com.hunk.route.infrastructure.cache.CacheParam;
import com.hunk.route.infrastructure.cache.Key;
import com.hunk.route.infrastructure.cache.Query;
import com.hunk.route.infrastructure.cache.RedisCache;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @author Zhao Peng
 * @date 2022/4/13
 *     <p>
 */
public class RedisTest extends ApplicationTests {

    @Resource RedisCache redisCache;
    @Resource RedisTemplate<String, Object> redisTemplate;

    @Test
    public void zSetAdd() {
        final BankName bankName = new BankName("ICBC", "银行");
        BankInfoEvent paramE = new BankInfoEvent("11111", bankName, CardType.DebitCard);
        Key key = new Key(RouteConstants.BANK_INFO_KEY_, paramE.getBankId());
        final CacheParam<BankInfoEvent> param = new CacheParam<>(key, paramE);
        redisCache.addCache(param);
    }

    @Test
    public void delete() {
        redisCache.deleteCache(RouteConstants.BANK_INFO_KEY_);
    }

    @Test
    public void query() {
        Query.Param param = new Query.Param(1, 5, RouteConstants.BANK_INFO_KEY_);
        final Query.Result<BankInfoEvent> bankInfoEventResult =
                redisCache.queryCache(param, BankInfoEvent.class);
        System.out.println(JSONObject.toJSONString(bankInfoEventResult));
    }
}
