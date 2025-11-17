package com.danielpg.desafio.infrastructure.controller;

import com.danielpg.desafio.application.order.CreateOrderUseCase;
import com.danielpg.desafio.domain.OrderItem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Operation(summary = "Cria um novo pedido")
    @PostMapping
    public OrderResponse create(@RequestBody CreateOrderRequest request) {
        return OrderResponse.from(createOrderUseCase.execute(request.toUseCaseRequest()));
    }

    @Schema(description = "Request para criação de pedido")
    public record CreateOrderRequest(Long id, List<Item> items) {

        public List<OrderItem> toDomainItems() {
            return items.stream()
                    .map(i -> new OrderItem(i.sku(), i.quantity(), i.price()))
                    .toList();
        }

        public CreateOrderUseCase.Request toUseCaseRequest() {
            return new CreateOrderUseCase.Request(id(), toDomainItems());
        }

        @Schema(description = "Item do pedido")
        public record Item(String sku, Integer quantity, BigDecimal price) {}
    }
}
