package com.danielpg.desafio.application.order;

import com.danielpg.desafio.domain.order.Order;
import com.danielpg.desafio.domain.order.OrderRepository;

public class GetOrderUseCase {

    private final OrderRepository repository;

    public GetOrderUseCase(OrderRepository repository) {
        this.repository = repository;
    }

    public Order execute(Long id) {
        return repository.getByIdOrThrow(id);
    }
}
