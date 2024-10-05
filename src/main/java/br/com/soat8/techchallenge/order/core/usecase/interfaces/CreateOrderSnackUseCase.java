package br.com.soat8.techchallenge.order.core.usecase.interfaces;

import br.com.soat8.techchallenge.order.controller.DTO.OrderSnackRequest;

public interface CreateOrderSnackUseCase {
    byte[] requestOrder(OrderSnackRequest orderSnackRequest);

}
