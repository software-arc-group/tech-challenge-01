package br.com.soat8.techchallenge.port.out;

import br.com.soat8.techchallenge.domain_old.Product;
import br.com.soat8.techchallenge.domain_old.ProductCategory;

import java.util.UUID;

public interface ProductPort {

    void saveProduct(Product product, ProductCategory productCategory);
    void removeProduct(UUID productId);
    Boolean findById(UUID productId);

}