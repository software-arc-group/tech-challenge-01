package br.com.soat8.techchallenge.item.adapter.repository;

import br.com.soat8.techchallenge.order.core.entities.OrderSnackItem;

public interface OrderSnackItemPort {
    public OrderSnackItem getOrderSnackItemById(String id);
}
