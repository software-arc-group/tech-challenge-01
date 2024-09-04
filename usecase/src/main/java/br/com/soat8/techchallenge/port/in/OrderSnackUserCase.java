package br.com.soat8.techchallenge.core_old.port.in;

import br.com.soat8.techchallenge.adapter_old.out.persistence.entity.enums.OrderProgress;
import br.com.soat8.techchallenge.domain_old.OrderSnack;

import java.util.List;

public interface OrderSnackUserCase {
    List<OrderSnack> listOrderSnack(OrderProgress progress, String cpf);
}
