package com.danielpg.desafio.domain;

import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> getById(Long id);
    Order getByIdOrThrow(Long id);
}
