package br.com.soat8.techchallenge.port.out;

import br.com.soat8.techchallenge.adapter_old.out.persistence.entity.enums.OrderProgress;
import br.com.soat8.techchallenge.domain_old.OrderSnack;

import java.util.List;

public interface OrderSnackPort {

    List<OrderSnack> listOrderSnack(OrderProgress progress, String cpf);

    void saveOrderSnack(OrderSnack orderSnack);
}
