package br.com.soat8.techchallenge.order.adapters.repository;

import br.com.soat8.techchallenge.order.core.entities.enums.OrderProgress;
import br.com.soat8.techchallenge.order.core.entities.OrderSnack;
import br.com.soat8.techchallenge.product.core.entities.Product;

import java.util.List;
import java.util.UUID;

public interface OrderSnackPort {

    List<OrderSnack> listOrderSnack(OrderProgress progress, String cpf);

    void saveOrderSnack(OrderSnack orderSnack, UUID externalReference);

    void updateOrderSnackProgress (OrderProgress orderProgress, UUID orderSnackId);

    OrderProgress getOrderSnackProgress (UUID orderSnackId);

}
