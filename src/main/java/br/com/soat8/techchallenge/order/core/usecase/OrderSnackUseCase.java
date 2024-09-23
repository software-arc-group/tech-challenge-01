package br.com.soat8.techchallenge.order.core.usecase;

import br.com.soat8.techchallenge.order.controller.DTO.OrderProgressRequest;
import br.com.soat8.techchallenge.order.controller.DTO.OrderSnackRequest;
import br.com.soat8.techchallenge.order.core.entities.enums.OrderProgress;
import br.com.soat8.techchallenge.order.core.entities.OrderSnack;

import java.util.List;

public interface OrderSnackUseCase {
    byte[] requestOrder(OrderSnackRequest orderSnackRequest);

    List<OrderSnack> listOrderSnack(OrderProgressRequest progress, String cpf);
}
