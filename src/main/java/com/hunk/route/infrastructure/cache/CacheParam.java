package com.hunk.route.infrastructure.cache;

import lombok.Getter;

import java.util.concurrent.TimeUnit;

/**
 * @author Zhao Peng
 * @date 2022/4/15
 *     <p>
 */
@Getter
public class CacheParam<T> {

    private final Key key;

    private final T data;

    private final TimeOut timeout;

    public CacheParam(Key key, T data) {
        this(key, data, new TimeOut());
    }

    public CacheParam(Key key, T data, TimeOut timeout) {
        this.key = key;
        this.data = data;
        this.timeout = timeout;
    }

    @Getter
    public static class TimeOut {

        private final Long timeout;

        private final TimeUnit timeUnit;

        public TimeOut() {
            this.timeout = 60L;
            this.timeUnit = TimeUnit.MINUTES;
        }

        public TimeOut(Long timeout, TimeUnit timeUnit) {
            this.timeout = timeout;
            this.timeUnit = timeUnit;
        }
    }
}
