package br.com.soat8.techchallenge.core.port.in;

import br.com.soat8.techchallenge.domain.Product;

public interface ProductUseCase {

    void saveProduct(Product product);
    void updateProduct(Product product);
    void removeProduct(Integer product_id);

}