package com.danielpg.desafio.domain.order;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void total_shouldReturnSumOfAllItems() {
        OrderItem item1 = new OrderItem("SKU1", 2, new BigDecimal("10"));
        OrderItem item2 = new OrderItem("SKU2", 3, new BigDecimal("5"));
        Order order = new Order(List.of(item1, item2));

        BigDecimal total = order.total();

        assertEquals(new BigDecimal("35"), total);
    }

    @Test
    void finalizeCreation_shouldSetIdOnce() {
        Order order = new Order(List.of());
        order.finalizeCreation(1L);
        assertEquals(1L, order.id());

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> order.finalizeCreation(2L)
        );
        assertEquals("Criação do pedido já finalizada.", exception.getMessage());
    }

    @Test
    void items_shouldBeUnmodifiable() {
        OrderItem item = new OrderItem("SKU", 1, BigDecimal.ONE);
        Order order = new Order(List.of(item));
        List<OrderItem> items = order.items();

        assertThrows(
                UnsupportedOperationException.class,
                () -> items.add(item)
        );
    }

    @Test
    void constructor_withId_shouldSetIdAndItems() {
        OrderItem item = new OrderItem("SKU", 1, BigDecimal.ONE);
        Order order = new Order(42L, List.of(item));

        assertEquals(42L, order.id());
        assertEquals(1, order.items().size());
        assertEquals(item, order.items().getFirst());
    }

    @Test
    void constructor_withoutId_shouldSetIdNullAndItems() {
        OrderItem item = new OrderItem("SKU", 1, BigDecimal.ONE);
        Order order = new Order(List.of(item));

        assertNull(order.id());
        assertEquals(1, order.items().size());
        assertEquals(item, order.items().getFirst());
    }
}
