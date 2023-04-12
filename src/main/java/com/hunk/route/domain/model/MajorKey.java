package com.hunk.route.domain.model;

import java.util.UUID;

/**
 * @author hunk
 * @date 2022/4/2
 *     <p>主键
 */
public class MajorKey {

    public static String getId() {
        final String random = UUID.randomUUID().toString().toUpperCase();
        return random.substring(0, random.indexOf("-"));
    }
}
