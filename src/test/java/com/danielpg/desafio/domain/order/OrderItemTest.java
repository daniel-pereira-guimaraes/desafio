package com.danielpg.desafio.domain.order;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class OrderItemTest {

    @Test
    void shouldCreateOrderItemAndReturnCorrectValues() {
        OrderItem item = new OrderItem("SKU123", 5, new BigDecimal("10.50"));

        assertThat(item.sku()).isEqualTo("SKU123");
        assertThat(item.quantity()).isEqualTo(5);
        assertThat(item.price()).isEqualByComparingTo(new BigDecimal("10.50"));
    }

    @Test
    void shouldCalculateTotalCorrectly() {
        OrderItem item = new OrderItem("SKU123", 3, new BigDecimal("15.00"));

        BigDecimal expectedTotal = new BigDecimal("45.00");
        assertThat(item.total()).isEqualByComparingTo(expectedTotal);
    }

    @Test
    void totalShouldBeZeroWhenQuantityIsZero() {
        OrderItem item = new OrderItem("SKU123", 0, new BigDecimal("15.00"));

        assertThat(item.total()).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    void totalShouldBeZeroWhenPriceIsZero() {
        OrderItem item = new OrderItem("SKU123", 5, BigDecimal.ZERO);

        assertThat(item.total()).isEqualByComparingTo(BigDecimal.ZERO);
    }
}
