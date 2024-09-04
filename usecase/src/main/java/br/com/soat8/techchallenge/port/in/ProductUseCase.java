package br.com.soat8.techchallenge.core_old.port.in;

import br.com.soat8.techchallenge.domain_old.Product;

import java.util.UUID;

public interface ProductUseCase {

    void saveProduct(Product product);
    void removeProduct(UUID productId);

}