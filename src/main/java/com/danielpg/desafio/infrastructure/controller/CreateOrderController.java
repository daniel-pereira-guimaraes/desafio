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
}
