package br.com.soat8.techchallenge.order.core.usecase.interfaces;

import br.com.soat8.techchallenge.order.core.entities.OrderSnackItem;
import br.com.soat8.techchallenge.product.core.entities.Product;

public interface SaveOrderSnackItemUseCase {
    public OrderSnackItem save(Product product , Integer qtd);
}
