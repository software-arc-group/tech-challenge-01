package br.com.soat8.techchallenge.order.adapters.repository;

import br.com.soat8.techchallenge.order.core.entities.enums.OrderProgress;
import br.com.soat8.techchallenge.order.core.entities.OrderSnack;

import java.util.List;

public interface OrderSnackPort {

    List<OrderSnack> listOrderSnack(OrderProgress progress, String cpf);

    void saveOrderSnack(OrderSnack orderSnack);

}
