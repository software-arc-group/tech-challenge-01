package br.com.soat8.techchallenge.core.port.in;

import br.com.soat8.techchallenge.domain.OrderSnack;

public interface OrderSnackUseCase {
    byte[] requestOrder(OrderSnack orderSnack);
}
