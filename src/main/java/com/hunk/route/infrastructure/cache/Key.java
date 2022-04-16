package com.hunk.route.infrastructure.cache;

import lombok.Getter;

/**
 * @author Zhao Peng
 * @date 2022/4/16
 * <p>
 */
@Getter
public class Key {


    private final String keyPrefix;

    private final String keySuffix;

    public Key(String keyPrefix, String keySuffix) {
        this.keyPrefix = keyPrefix;
        this.keySuffix = keySuffix;
    }

    public String getFullKey() {
        return keyPrefix + keySuffix;
    }

}
