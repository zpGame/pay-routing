package com.hunk.route.domain.model.rule;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hunk
 * @date 2022/2/20
 *     <p>
 */
public interface RouteRuleRepository extends JpaRepository<RouteRule, Long> {

    RouteRule findRouteRuleByRuleId(String ruleId);

}
