package com.danielpg.desafio.infrastructure.config;

import com.danielpg.desafio.application.order.CreateOrderUseCase;
import com.danielpg.desafio.application.order.GetOrderUseCase;
import com.danielpg.desafio.domain.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringContext {

    @Autowired
    private OrderRepository orderRepository;

    @Bean
    public CreateOrderUseCase createOrderUseCase() {
        return new CreateOrderUseCase(orderRepository);
    }

    @Bean
    public GetOrderUseCase getOrderUseCase() {
        return new GetOrderUseCase(orderRepository);
    }
}
