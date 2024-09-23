package br.com.soat8.techchallenge.product.core.usecase.interfaces;

import br.com.soat8.techchallenge.product.controller.DTO.ProductRequest;

import java.util.UUID;

public interface ProductUseCase {

    void saveProduct(ProductRequest product);
    void removeProduct(UUID productId);

}