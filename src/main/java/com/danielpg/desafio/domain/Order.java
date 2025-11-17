package com.danielpg.desafio.domain;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class Order {

    private Long id;
    private final List<OrderItem> items;

    public Order(Long id, List<OrderItem> items) {
        this.id = id;
        this.items = Collections.unmodifiableList(items);
    }

    public Order(List<OrderItem> items) {
        this(null, items);
    }

    public Long id() {
        return id;
    }

    public void finalizeCreation(Long id) {
        if (this.id != null) {
            throw new IllegalStateException("Criação do pedido já finalizada.");
        }
        this.id = id;
    }

    public List<OrderItem> items() {
        return items;
    }

    public BigDecimal total() {
        return items.stream()
                .map(OrderItem::total)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
