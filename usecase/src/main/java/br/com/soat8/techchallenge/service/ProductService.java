package br.com.soat8.techchallenge.core_old.service;

import br.com.soat8.techchallenge.core_old.port.in.ProductUseCase;
import br.com.soat8.techchallenge.core_old.port.out.ProductCategoryPort;
import br.com.soat8.techchallenge.core_old.port.out.ProductPort;
import br.com.soat8.techchallenge.domain_old.Product;
import br.com.soat8.techchallenge.domain_old.ProductCategory;
import br.com.soat8.techchallenge.domain_old.exception.InvalidCategoryException;
import br.com.soat8.techchallenge.domain_old.exception.NotFoundProductException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class ProductService implements ProductUseCase {

    private final ProductPort productPort;

    private final ProductCategoryPort productCategoryPort;

    public ProductService(ProductPort productPort, ProductCategoryPort productCategoryPort) {
        this.productPort = productPort;
        this.productCategoryPort = productCategoryPort;
    }

    @Override
    public void saveProduct(Product product) {
        ProductCategory productCategory = getProductCategory(product);
        productPort.saveProduct(product, productCategory);
    }

    private ProductCategory getProductCategory(Product product) {
        ProductCategory productCategory = productCategoryPort.findProductCategory(product.getCategoryId());
        if (Objects.isNull(productCategory)) {
            throw new InvalidCategoryException("Invalid Category: " + product.getCategoryId());
        }
        return productCategory;
    }

    @Override
    public void removeProduct(UUID productId) {
        notFoundProduct(productId);
        productPort.removeProduct(productId);
    }


    private void notFoundProduct(UUID productId) {
        if (!productPort.findById(productId)) {
            throw new NotFoundProductException("This product does not exist Id: " + productId);
        }
    }

}