package br.com.soat8.techchallenge.product.core.usecase.interfaces;

import br.com.soat8.techchallenge.product.controller.DTO.ProductRequest;

public interface SaveProductUseCase {
    void saveProduct(ProductRequest product);
}