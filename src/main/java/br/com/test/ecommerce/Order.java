package br.com.test.ecommerce;

import java.math.BigDecimal;

/**
 * @author Cesar
 * @see br.com.test.ecommerce
 * @since 31/01/2021
 */
public class Order {

    private final String userId, orderId;

    private final BigDecimal amount;

    public Order(String userId, String orderId, BigDecimal amount) {
        this.userId = userId;
        this.orderId = orderId;
        this.amount = amount;
    }
}
