package com.hunk.route.infrastructure.cache;

import com.alibaba.fastjson.JSONObject;
import com.hunk.route.domain.model.route.RouteConstants;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Zhao Peng
 * @date 2022/4/12
 *       <p>
 *       Redis工具类
 */
public class RedisCache {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisCache(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void addCache(CacheParam<?> param) {
        final Key key = param.getKey();
        redisTemplate.opsForZSet().incrementScore(key.getKeyPrefix(), key.getKeySuffix(), 1);
        final CacheParam.TimeOut timeout = param.getTimeout();
        redisTemplate.opsForValue().set(key.getFullKey(), param.getData(), timeout.getTimeout(), timeout.getTimeUnit());
    }

    public void deleteCache(Key key) {
        redisTemplate.opsForZSet().remove(key.getKeyPrefix(), key.getKeySuffix());
        redisTemplate.delete(key.getFullKey());
    }

    /**
     * 分页查询
     *
     * @param param 查询参数
     * @param aClass 转换类
     * @param <T> 泛型
     *            <p>
     *            todo 目前逻辑有缺陷，后期改；目前不支持条件查询
     * @return 结果
     */
    public <T> Query.Result<T> pageQueryCache(Query.Param param, Class<T> aClass) {
        final String key = param.getKey();
        Set<Object> range = redisTemplate.opsForZSet().range(key, param.getStart(), param.getEnd());
        Objects.requireNonNull(range);
        final List<T> collect = range.stream().map(String::valueOf).map(id -> this.query(new Key(key, id), aClass))
            .collect(Collectors.toList());
        final Long aLong = redisTemplate.opsForZSet().zCard(RouteConstants.BANK_INFO_KEY_);
        return new Query.Result<>(collect, null == aLong ? 0 : aLong);
    }

    public <T> T query(Key key, Class<T> aClass) {
        JSONObject object = (JSONObject)redisTemplate.opsForValue().get(key.getFullKey());
        return null == object ? null : object.toJavaObject(aClass);
    }

    public void reviseCache(CacheParam<?> param) {
        Key key = param.getKey();
        CacheParam.TimeOut timeout = param.getTimeout();
        redisTemplate.opsForValue().set(key.getFullKey(), param.getData(), timeout.getTimeout(), timeout.getTimeUnit());
    }
}
