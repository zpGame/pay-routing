package com.hunk.route.domain;

/**
 * @author hunk
 * @date 2022/2/28
 *     <p>
 */
public class MerchantNotFoundException extends RuntimeException {

    public MerchantNotFoundException(long merchantId) {
        super("Merchant not found with id " + merchantId);
    }
}
