package br.com.soat8.techchallenge.product.core.usecase;

import br.com.soat8.techchallenge.product.core.entities.ProductCategory;

import java.util.UUID;

public interface ProductCategoryUseCase {
    ProductCategory findProductCategory(UUID productCategoryId);

}
