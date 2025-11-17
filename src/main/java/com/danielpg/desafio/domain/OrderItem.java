package com.danielpg.desafio.domain;

import java.math.BigDecimal;

public class OrderItem {

    private final String sku;
    private final Integer quantity;
    private final BigDecimal price;

    public OrderItem(String sku, Integer quantity, BigDecimal price) {
        this.sku = sku;
        this.quantity = quantity;
        this.price = price;
    }

    public String sku() {
        return sku;
    }

    public Integer quantity() {
        return quantity;
    }

    public BigDecimal price() {
        return price;
    }

    public BigDecimal total() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
