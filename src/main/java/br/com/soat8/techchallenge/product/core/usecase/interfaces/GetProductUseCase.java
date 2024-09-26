package br.com.soat8.techchallenge.product.core.usecase.interfaces;

import br.com.soat8.techchallenge.product.core.entities.Product;

import java.util.UUID;

public interface GetProductUseCase {
    Product getProduct(String id);
}
