package com.danielpg.desafio.infrastructure.controller;

import com.danielpg.desafio.application.order.CreateOrderUseCase;
import com.danielpg.desafio.domain.Order;
import com.danielpg.desafio.domain.OrderItem;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class CreateOrderController {

    private final CreateOrderUseCase createOrderUseCase;

    public CreateOrderController(CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @PostMapping
    public OrderResponse create(@RequestBody CreateOrderRequest request) {
        Order order = createOrderUseCase.execute(request.toUseCaseRequest());
        return OrderResponse.from(order);
    }

    public record CreateOrderRequest(Long id, List<Item> items) {

        public List<OrderItem> toDomainItems() {
            return items.stream()
                    .map(i -> new OrderItem(i.sku(), i.quantity(), i.price()))
                    .toList();
        }

        public CreateOrderUseCase.Request toUseCaseRequest() {
            return new CreateOrderUseCase.Request(id(), toDomainItems());
        }

        public record Item(String sku, Integer quantity, BigDecimal price) {}
    }

    public record OrderResponse(Long id, List<OrderItemResponse> items, BigDecimal total) {

        public static OrderResponse from(Order order) {
            List<OrderItemResponse> itemResponses = order.items().stream()
                    .map(i -> new OrderItemResponse(i.sku(), i.quantity(), i.price(), i.total()))
                    .toList();
            return new OrderResponse(order.id(), itemResponses, order.total());
        }

        public record OrderItemResponse(String sku, Integer quantity, BigDecimal price, BigDecimal total) {}
    }
}
