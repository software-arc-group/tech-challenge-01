package br.com.soat8.techchallenge.core.port.in;

import br.com.soat8.techchallenge.entities.ProductCategory;

import java.util.UUID;

public interface ProductCategoryUseCase {
    ProductCategory findProductCategory(UUID productCategoryId);

}
