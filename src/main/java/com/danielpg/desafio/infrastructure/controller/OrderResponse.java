package com.danielpg.desafio.infrastructure.controller;

import com.danielpg.desafio.domain.Order;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Representa um pedido com itens e total")
public record OrderResponse(
        @Schema(description = "ID do pedido") Long id,
        @Schema(description = "Lista de itens do pedido") List<OrderItemResponse> items,
        @Schema(description = "Valor total do pedido") BigDecimal total
) {

    public static OrderResponse from(Order order) {
        List<OrderItemResponse> itemResponses = order.items().stream()
                .map(i -> new OrderItemResponse(i.sku(), i.quantity(), i.price(), i.total()))
                .toList();
        return new OrderResponse(order.id(), itemResponses, order.total());
    }

    @Schema(description = "Representa um item do pedido")
    public record OrderItemResponse(
            @Schema(description = "SKU do produto") String sku,
            @Schema(description = "Quantidade do item") Integer quantity,
            @Schema(description = "Preço unitário do item") BigDecimal price,
            @Schema(description = "Total do item (preço x quantidade)") BigDecimal total
    ) {}
}
