package br.com.soat8.techchallenge.order.core.usecase;

import br.com.soat8.techchallenge.order.core.entities.OrderSnackItem;
import br.com.soat8.techchallenge.order.core.usecase.interfaces.SaveOrderSnackItemUseCase;
import br.com.soat8.techchallenge.product.core.entities.Product;
import br.com.soat8.techchallenge.product.utils.ProductMapper;

import java.math.BigDecimal;

public class SaveOrderSnackItemService implements SaveOrderSnackItemUseCase {

    public ProductMapper productMapper;

    @Override
    public OrderSnackItem save(Product product, Integer qtd) {
        new OrderSnackItem(null, product.getPrice().multiply(BigDecimal.valueOf(qtd)) , product, qtd );

    }
}
