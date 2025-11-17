package com.danielpg.desafio.infrastructure.controller;

import com.danielpg.desafio.application.order.GetOrderUseCase;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class GetOrderController {

    private final GetOrderUseCase getOrderUseCase;

    public GetOrderController(GetOrderUseCase getOrderUseCase) {
        this.getOrderUseCase = getOrderUseCase;
    }

    @Operation(summary = "Retorna os detalhes de um pedido pelo ID")
    @GetMapping("/{id}")
    public OrderResponse get(@PathVariable Long id) {
        return OrderResponse.from(getOrderUseCase.execute(id));
    }
}
