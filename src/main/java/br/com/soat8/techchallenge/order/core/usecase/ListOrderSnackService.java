package br.com.soat8.techchallenge.order.core.usecase;

import br.com.soat8.techchallenge.order.adapters.repository.OrderSnackPort;
import br.com.soat8.techchallenge.order.core.entities.OrderSnack;
import br.com.soat8.techchallenge.order.core.entities.enums.OrderProgress;
import br.com.soat8.techchallenge.order.core.usecase.interfaces.ListOrderSnackUseCase;
import br.com.soat8.techchallenge.order.utils.OrderProgressMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class ListOrderSnackService implements ListOrderSnackUseCase {

    @Autowired
    private final OrderSnackPort orderSnackPort;

    @Autowired
    private final OrderProgressMapper progressMapper;

    @Override
    public List<OrderSnack> listOrderSnack(OrderProgress progress, String cpf) {
        OrderProgress orderProgress = progressMapper.toOrderProgress(progress);
        List<OrderSnack> orderSnacks = orderSnackPort.listOrderSnack(orderProgress, cpf);

        orderSnacks.sort(Comparator.comparingInt(this::getOrderProgressPriority).thenComparing(OrderSnack::getCreatedAt));

        return orderSnacks;
    }

    private int getOrderProgressPriority(OrderSnack orderSnack) {
        switch (orderSnack.getProgress()) {
            case "RECEIVED":
                return 1;
            case "IN_PREPARATION":
                return 2;
            case "READY":
                return 3;
            case "FINISHED":
                return 4;
            default:
                return Integer.MAX_VALUE; // Para casos inesperados
        }
    }
}
