package com.danielpg.desafio.infrastructure.controller;

import com.danielpg.desafio.domain.Order;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponse(Long id, List<OrderItemResponse> items, BigDecimal total) {

    public static OrderResponse from(Order order) {
        List<OrderItemResponse> itemResponses = order.items().stream()
                .map(i -> new OrderItemResponse(i.sku(), i.quantity(), i.price(), i.total()))
                .toList();
        return new OrderResponse(order.id(), itemResponses, order.total());
    }

    public record OrderItemResponse(String sku, Integer quantity, BigDecimal price, BigDecimal total) {}
}
