package br.com.soat8.techchallenge.core.port.out;

import br.com.soat8.techchallenge.domain.Customer;
import br.com.soat8.techchallenge.domain.Product;
import br.com.soat8.techchallenge.domain.ProductCategory;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductPort {

    void saveProduct(Product product);
    void updateProduct(Product product);
    void removeProduct(UUID productId);
    Boolean findById(UUID productId);

}