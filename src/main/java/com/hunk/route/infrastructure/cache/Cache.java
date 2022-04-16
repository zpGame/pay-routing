package com.hunk.route.infrastructure.cache;

/**
 * @author Zhao Peng
 * @date 2022/4/12
 * <p> 缓存接口
 */
public interface Cache<T> {

    boolean addCache(T t);



}
