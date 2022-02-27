package com.hunk.route.domain;

/**
 * @author hunk
 * @date 2022/2/27
 * <p>
 */
public class BankInfoNotFoundException extends RuntimeException {

    public BankInfoNotFoundException(long bankInfoId) {
        super("BankInfo not found with id " + bankInfoId);
    }
}
