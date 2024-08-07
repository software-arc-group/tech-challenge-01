package br.com.soat8.techchallenge.core.port.out;

import br.com.soat8.techchallenge.domain.OrderSnack;

public interface OrderSnackPort {
    void saveOrderSnack(OrderSnack orderSnack);
}
