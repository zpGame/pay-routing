package com.hunk.route.domain.event;

import com.google.common.eventbus.Subscribe;
import com.hunk.route.domain.model.route.RouteConstants;
import com.hunk.route.infrastructure.cache.CacheParam;
import com.hunk.route.infrastructure.cache.Key;
import com.hunk.route.infrastructure.cache.RedisCache;
import com.hunk.route.infrastructure.messaging.event.EventSupportListen;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author hunk
 * @date 2022/4/5
 *     <p>
 */
public class BankInfoListen extends EventSupportListen<BankInfoEvent> {

    @Resource private RedisCache redisCache;

    @Override
    @Subscribe
    public Map<String, Object> onEvent(BankInfoEvent paramE) {
        Key key = new Key(RouteConstants.BANK_INFO_KEY_, paramE.getBankId());
        final CacheParam<BankInfoEvent> param = new CacheParam<>(key, paramE);
        redisCache.addCache(param);
        return null;
    }
}
