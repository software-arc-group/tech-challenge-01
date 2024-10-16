package br.com.soat8.techchallenge.order.core.usecase;

import br.com.soat8.techchallenge.order.adapters.repository.OrderSnackPort;
import br.com.soat8.techchallenge.order.core.entities.enums.OrderProgress;
import br.com.soat8.techchallenge.order.core.usecase.interfaces.GetOrderSnackProgressUseCase;
import br.com.soat8.techchallenge.order.utils.OrderProgressMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GetOrderSnackProgressService implements GetOrderSnackProgressUseCase {

    @Autowired
    private final OrderSnackPort orderSnackPort;

    @Override
    public OrderProgress getOrderSnackProgress(UUID orderSnackId) {
        return orderSnackPort.getOrderSnackProgress(orderSnackId);
    }

}
