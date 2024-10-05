package br.com.soat8.techchallenge.product.core.usecase.interfaces;

import br.com.soat8.techchallenge.product.controller.DTO.ProductRequest;
import br.com.soat8.techchallenge.product.core.entities.Product;

public interface UpdateProductUseCase {
    Product update(ProductRequest productRequest);
}
