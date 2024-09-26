package br.com.soat8.techchallenge.order.core.usecases.in;

import br.com.soat8.techchallenge.adapter.out.persistence.entity.enums.OrderProgress;
import br.com.soat8.techchallenge.domain.OrderSnack;

import java.util.List;

public interface OrderSnackUseCase {
    byte[] requestOrder(OrderSnack orderSnack);

    List<OrderSnack> listOrderSnack(OrderProgress progress, String cpf);
}
