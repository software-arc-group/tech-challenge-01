package br.com.soat8.techchallenge.core.port.in;

import br.com.soat8.techchallenge.order.core.entities.enums.OrderProgress;
import br.com.soat8.techchallenge.order.core.entities.OrderSnack;

import java.util.List;

public interface OrderSnackUserCase {
    List<OrderSnack> listOrderSnack(OrderProgress progress, String cpf);
}
