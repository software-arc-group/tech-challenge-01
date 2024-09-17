package br.com.soat8.techchallenge.product.core.usecase;

import br.com.soat8.techchallenge.product.core.entities.Product;

import java.util.UUID;

public interface ProductUseCase {

    void saveProduct(Product product);
    void removeProduct(UUID productId);

}