package br.com.soat8.techchallenge.order.core.usecase;

import br.com.soat8.techchallenge.order.adapters.repository.OrderSnackPort;
import br.com.soat8.techchallenge.order.controller.DTO.OrderProgressRequest;
import br.com.soat8.techchallenge.order.core.entities.OrderSnack;
import br.com.soat8.techchallenge.order.core.entities.enums.OrderProgress;
import br.com.soat8.techchallenge.order.core.usecase.interfaces.ListOrderSnackUseCase;
import br.com.soat8.techchallenge.order.utils.OrderProgressMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ListOrderSnackService implements ListOrderSnackUseCase {
    @Autowired
    private final OrderSnackPort orderSnackPort;
    @Autowired
    private final OrderProgressMapper progressMapper;

    @Override
    public List<OrderSnack> listOrderSnack(OrderProgressRequest progress, String cpf) {
        OrderProgress orderProgress = progressMapper.toOrderProgress(progress);
        return orderSnackPort.listOrderSnack(orderProgress, cpf);
    }

}
