package br.com.soat8.techchallenge.order.core.usecases.out;

import br.com.soat8.techchallenge.adapter.out.persistence.entity.enums.OrderProgress;
import br.com.soat8.techchallenge.domain.OrderSnack;

import java.util.List;
import java.util.UUID;

public interface OrderSnackPort {

    List<OrderSnack> listOrderSnack(OrderProgress progress, String cpf);

    void saveOrderSnack(OrderSnack orderSnack, UUID externalOrderId);
}
