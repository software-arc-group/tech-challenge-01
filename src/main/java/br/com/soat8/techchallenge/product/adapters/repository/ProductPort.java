package br.com.soat8.techchallenge.product.adapters.repository;

import br.com.soat8.techchallenge.product.core.entities.Product;
import br.com.soat8.techchallenge.product.core.entities.ProductCategory;

import java.util.UUID;

public interface ProductPort {

    void saveProduct(Product product, ProductCategory productCategory);

    void removeProduct(UUID productId);
    Boolean findById(UUID productId);
    Product getById(UUID productId);

}