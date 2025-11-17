package com.danielpg.desafio.application;

import com.danielpg.desafio.domain.Order;
import com.danielpg.desafio.domain.OrderItem;
import com.danielpg.desafio.domain.OrderRepository;

import java.util.List;

public class CreateOrderUseCase {

    private final OrderRepository repository;


    public CreateOrderUseCase(OrderRepository repository) {
        this.repository = repository;
    }

    /* Em um sistema real, o tratamento de transação seria feito aqui.
        Verificação de permissões também seriam feitas aqui na camada application.
     */
    public Order execute(Request request) {
        Order order = new Order(request.id, request.items);
        return repository.save(order);
    }

    public record Request(Long id, List<OrderItem> items) {}
}
