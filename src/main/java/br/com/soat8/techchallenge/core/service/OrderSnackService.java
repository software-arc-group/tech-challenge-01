package br.com.soat8.techchallenge.core.service;

import br.com.soat8.techchallenge.core.port.in.OrderSnackUseCase;
import br.com.soat8.techchallenge.domain.OrderSnack;

public class OrderSnackService implements OrderSnackUseCase {
    @Override
    public byte[] requestOrder(OrderSnack orderSnack) {
        return new byte[0];
    }
}
