package br.com.soat8.techchallenge.order.core.usecase.interfaces;

import br.com.soat8.techchallenge.order.core.entities.enums.OrderProgress;

public interface GetOrderSnackProgressUseCase {
    OrderProgress getOrderSnackProgress (String orderSnackId);
}
