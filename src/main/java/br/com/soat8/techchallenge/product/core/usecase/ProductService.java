package br.com.soat8.techchallenge.product.core.usecase;

import br.com.soat8.techchallenge.product.adapters.repository.ProductCategoryPort;
import br.com.soat8.techchallenge.product.adapters.repository.ProductPort;
import br.com.soat8.techchallenge.product.controller.DTO.ProductRequest;
import br.com.soat8.techchallenge.product.core.entities.Product;
import br.com.soat8.techchallenge.product.core.entities.ProductCategory;
import br.com.soat8.techchallenge.product.core.exceptions.InvalidCategoryException;
import br.com.soat8.techchallenge.product.core.exceptions.NotFoundProductException;
import br.com.soat8.techchallenge.product.core.usecase.interfaces.ProductUseCase;
import br.com.soat8.techchallenge.product.utils.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductService implements ProductUseCase {

    @Autowired
    private final ProductPort productPort;

    @Autowired
    private final ProductCategoryPort productCategoryPort;

    @Autowired
    private final ProductMapper productMapper;

    @Override
    public void saveProduct(ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
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