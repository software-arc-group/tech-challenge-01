package br.com.soat8.techchallenge.order.core.usecase.interfaces;

import br.com.soat8.techchallenge.order.core.entities.OrderSnack;
import br.com.soat8.techchallenge.order.core.entities.enums.OrderProgress;

import java.util.List;

public interface ListOrderSnackUseCase {

    public List<OrderSnack> listOrderSnack(OrderProgress progress, String cpf);

}
