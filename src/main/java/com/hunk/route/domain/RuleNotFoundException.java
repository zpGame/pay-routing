package com.hunk.route.domain;

/**
 * @author hunk
 * @date 2022/2/24
 *     <p>
 */
public class RuleNotFoundException extends RuntimeException {

    public RuleNotFoundException(long ruleId) {
        super("Rule not found with id " + ruleId);
    }
}
