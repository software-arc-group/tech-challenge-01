package br.com.soat8.techchallenge.order.core.usecase.interfaces;

import br.com.soat8.techchallenge.order.core.entities.enums.OrderProgress;

import java.util.UUID;

public interface UpdateOrderSnackProgressUseCase {
    void updateOrderSnackProgress (OrderProgress orderProgress, UUID orderSnackId);
}
