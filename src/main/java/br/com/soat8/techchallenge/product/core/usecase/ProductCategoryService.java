package br.com.soat8.techchallenge.product.core.usecase;

import br.com.soat8.techchallenge.product.adapters.repository.ProductCategoryPort;
import br.com.soat8.techchallenge.product.core.entities.ProductCategory;
import br.com.soat8.techchallenge.product.core.exceptions.ProductCategoryNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class ProductCategoryService implements ProductCategoryUseCase {

    private final ProductCategoryPort productCategoryPort;

    public ProductCategoryService(ProductCategoryPort productCategoryPort) {
        this.productCategoryPort = productCategoryPort;
    }

    @Override
    public ProductCategory findProductCategory(UUID productCategoryId) {
        ProductCategory productCategory = productCategoryPort.findProductCategory(productCategoryId);
        categoryNotFound(productCategory);
        return productCategory;
    }

    private void categoryNotFound(ProductCategory productCategory) {
        if(Objects.isNull(productCategory)){
            throw new ProductCategoryNotFoundException("Product Category not found");
        }
    }

}