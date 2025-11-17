package com.danielpg.desafio.infrastructure.controller;

import com.danielpg.desafio.application.order.GetOrderUseCase;
import com.danielpg.desafio.domain.Order;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class GetOrderController {

    private final GetOrderUseCase getOrderUseCase;

    public GetOrderController(GetOrderUseCase getOrderUseCase) {
        this.getOrderUseCase = getOrderUseCase;
    }

    @GetMapping("/{id}")
    public OrderResponse get(@PathVariable Long id) {
        Order order = getOrderUseCase.execute(id);
        return OrderResponse.from(order);
    }
}
