package com.hunk.route.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author hunk
 * @date 2022/2/24
 *     <p>
 */
public class MoneyTest {

    private Money money;

    @Before
    public void initMoney() {
        money = new Money(10);
    }

    @Test
    public void add() {
        System.out.println(money.add(new Money(1)));
    }

    @Test
    public void isGreaterThanOrEqual() {
        System.out.println(money.isGreaterThanOrEqual(new Money(11)));
    }

    @Test
    public void asString() {}

    @Test
    public void multiply() {}

    @Test
    public void asLong() {}
}
