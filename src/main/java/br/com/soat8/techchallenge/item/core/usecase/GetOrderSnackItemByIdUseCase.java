package br.com.soat8.techchallenge.item.core.usecase;

import br.com.soat8.techchallenge.order.core.entities.OrderSnackItem;

public interface GetOrderSnackItemByIdUseCase {
    public OrderSnackItem getOrderSnackItem(String id);
}
