package br.com.soat8.techchallenge.port.in;

import br.com.soat8.techchallenge.domain_old.ProductCategory;

import java.util.UUID;

public interface ProductCategoryUseCase {
    ProductCategory findProductCategory(UUID productCategoryId);

}
