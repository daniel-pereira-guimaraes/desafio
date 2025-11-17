package com.danielpg.desafio.infrastructure;

import com.danielpg.desafio.domain.Order;
import com.danielpg.desafio.domain.OrderNotFoundException;
import com.danielpg.desafio.domain.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryOrderRepository implements OrderRepository {

    private static final AtomicLong autoIncId = new AtomicLong(0);
    private static final Map<Long, Order> data = new ConcurrentHashMap<>();

    @Override
    public Order save(Order order) {
        if (order.id() == null) {
            order.finalizeCreation(autoIncId.incrementAndGet());
        }
        data.put(order.id(), order);
        return order;
    }

    @Override
    public Optional<Order> getById(Long id) {
        return Optional.ofNullable(data.get(id));

    }

    @Override
    public Order getByIdOrThrow(Long id) {
        return getById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }
}
