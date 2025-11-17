package com.danielpg.desafio.application.order;

import com.danielpg.desafio.domain.order.Order;
import com.danielpg.desafio.domain.order.OrderItem;
import com.danielpg.desafio.domain.order.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateOrderUseCaseTest {

    private OrderRepository repository;
    private CreateOrderUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = mock(OrderRepository.class);
        useCase = new CreateOrderUseCase(repository);
    }

    @Test
    void execute_shouldSaveOrderWithoutId() {
        List<OrderItem> items = List.of(new OrderItem("SKU1", 2, new BigDecimal("10")));
        CreateOrderUseCase.Request request = new CreateOrderUseCase.Request(null, items);

        Order savedOrder = new Order(1L, items);
        when(repository.save(any(Order.class))).thenReturn(savedOrder);

        Order result = useCase.execute(request);

        ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);
        verify(repository).save(captor.capture());
        Order captured = captor.getValue();

        assertNull(request.id());
        assertEquals(items, captured.items());
        assertEquals(savedOrder, result);
    }

    @Test
    void execute_shouldSaveOrderWithId() {
        List<OrderItem> items = List.of(new OrderItem("SKU2", 1, new BigDecimal("5")));
        CreateOrderUseCase.Request request = new CreateOrderUseCase.Request(100L, items);

        Order savedOrder = new Order(100L, items);
        when(repository.save(any(Order.class))).thenReturn(savedOrder);

        Order result = useCase.execute(request);

        ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);
        verify(repository).save(captor.capture());
        Order captured = captor.getValue();

        assertEquals(100L, request.id());
        assertEquals(items, captured.items());
        assertEquals(savedOrder, result);
    }
}
