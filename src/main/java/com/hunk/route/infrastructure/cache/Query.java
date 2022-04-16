package com.hunk.route.infrastructure.cache;

import lombok.Getter;

import java.util.List;

/**
 * @author Zhao Peng
 * @date 2022/4/16
 *     <p>
 */
public class Query {

    public static class Param {

        private final long page;

        private final long size;

        private final String key;

        public Param(long page, long size, String key) {
            this.page = page;
            this.size = size;
            this.key = key;
        }

        public long getStart() {
            return 1 == page ? 0 : (page - 1) * size;
        }

        public long getEnd() {
            return page * size - 1;
        }

        public String getKey() {
            return key;
        }
    }

    @Getter
    public static class Result<T> {

        private final List<T> content;

        private final long total;

        public Result(List<T> content, long total) {
            this.content = content;
            this.total = total;
        }
    }
}
