package br.com.soat8.techchallenge.product.core.usecase;

import br.com.soat8.techchallenge.product.adapters.repository.ProductCategoryPort;
import br.com.soat8.techchallenge.product.core.entities.ProductCategory;
import br.com.soat8.techchallenge.product.core.exceptions.ProductCategoryNotFoundException;
import br.com.soat8.techchallenge.product.core.usecase.interfaces.ProductCategoryUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GetProductCategoryService implements ProductCategoryUseCase {

    @Autowired
    private final ProductCategoryPort productCategoryPort;

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