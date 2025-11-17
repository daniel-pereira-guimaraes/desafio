package com.danielpg.desafio.domain;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long id) {
        super("Pedido com id %d não encontrado.".formatted(id));
    }

}
