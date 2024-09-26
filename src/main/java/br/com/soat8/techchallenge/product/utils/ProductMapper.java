package br.com.soat8.techchallenge.product.utils;

import br.com.soat8.techchallenge.product.adapters.repository.entities.ProductEntity;
import br.com.soat8.techchallenge.product.controller.DTO.ProductRequest;
import br.com.soat8.techchallenge.product.core.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toRequest(ProductRequest productRequest);
    Product toProduct(ProductEntity entity);
    ProductEntity toEntity(Product product);
}
