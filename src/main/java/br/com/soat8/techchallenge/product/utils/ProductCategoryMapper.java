package br.com.soat8.techchallenge.product.utils;

import br.com.soat8.techchallenge.product.adapters.repository.entities.ProductCategoryEntity;
import br.com.soat8.techchallenge.product.adapters.repository.entities.ProductEntity;
import br.com.soat8.techchallenge.product.core.entities.Product;
import br.com.soat8.techchallenge.product.core.entities.ProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper {
    ProductCategory toProductCategory(ProductCategoryEntity productCategoryEntity);
    @Mappings({
            @Mapping(target = "category", ignore = true),
            @Mapping(target = "orderItems", ignore = true)
    })
    Product productEntityToProduct(ProductEntity productEntity);
    ProductCategoryEntity toEntity(ProductCategory product);
    @Mappings({
            @Mapping(target = "category", ignore = true),
            @Mapping(target = "orderItems", ignore = true)
    })
    ProductEntity productToProductEntity(Product productEntity);
}
