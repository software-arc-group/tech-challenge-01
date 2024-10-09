package br.com.soat8.techchallenge.product.core.usecase;

import br.com.soat8.techchallenge.product.adapters.repository.ProductPort;
import br.com.soat8.techchallenge.product.core.entities.Product;
import br.com.soat8.techchallenge.product.core.usecase.interfaces.GetProductUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetProductService implements GetProductUseCase {

    @Autowired
    ProductPort productPort;

    @Override
    public Product getProduct(String id) {
        return productPort.getById(UUID.fromString(id));

    }
}
