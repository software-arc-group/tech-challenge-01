package br.com.soat8.techchallenge.port.out;

import br.com.soat8.techchallenge.domain_old.ProductCategory;

import java.util.UUID;

public interface ProductCategoryPort {

    ProductCategory findProductCategory(UUID productCategoryId);

}