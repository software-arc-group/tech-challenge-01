package br.com.soat8.techchallenge.core.service;

import br.com.soat8.techchallenge.adapter.out.persistence.entity.enums.OrderProgress;
import br.com.soat8.techchallenge.core.port.in.OrderSnackUserCase;
import br.com.soat8.techchallenge.core.port.out.OrderSnackPort;
import br.com.soat8.techchallenge.domain.OrderSnack;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderSnackService implements OrderSnackUserCase {

    private final OrderSnackPort orderSnackPort;

    public OrderSnackService(OrderSnackPort orderSnackPort) {
        this.orderSnackPort = orderSnackPort;
    }

    @Override
    public List<OrderSnack> listOrderSnack(OrderProgress progress, String cpf) {
        return orderSnackPort.listOrderSnack(progress, cpf);
    }
}
