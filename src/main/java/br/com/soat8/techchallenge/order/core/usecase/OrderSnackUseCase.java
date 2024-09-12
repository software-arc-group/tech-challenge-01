package br.com.soat8.techchallenge.order.core.usecase;

import br.com.soat8.techchallenge.order.core.entities.enums.OrderProgress;
import br.com.soat8.techchallenge.order.core.entities.OrderSnack;

import java.util.List;

public interface OrderSnackUseCase {
    byte[] requestOrder(OrderSnack orderSnack);

    List<OrderSnack> listOrderSnack(OrderProgress progress, String cpf);
}
