package br.com.soat8.techchallenge.item.adapter;

import br.com.soat8.techchallenge.order.core.entities.OrderSnackItem;

import java.util.UUID;

public interface OrderSnackItemPort {
    public OrderSnackItem getOrderSnackItemById(UUID id);
    public Boolean existsSnackItemById(UUID id);
}
