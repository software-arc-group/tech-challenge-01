package br.com.soat8.techchallenge.product.adapters.repository;

import br.com.soat8.techchallenge.product.core.entities.ProductCategory;

import java.util.UUID;

public interface ProductCategoryPort {

    ProductCategory findProductCategory(UUID productCategoryId);

}