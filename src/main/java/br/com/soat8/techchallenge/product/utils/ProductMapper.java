package br.com.soat8.techchallenge.product.utils;

import br.com.soat8.techchallenge.order.adapters.repository.entities.OrderSnackItemEntity;
import br.com.soat8.techchallenge.order.core.entities.OrderSnackItem;
import br.com.soat8.techchallenge.product.adapters.repository.entities.ProductEntity;
import br.com.soat8.techchallenge.product.controller.DTO.ProductRequest;
import br.com.soat8.techchallenge.product.core.entities.Product;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductRequest productRequest);
    @Mappings({
        @Mapping(target = "category.products", ignore = true),
    })
    Product toProduct(ProductEntity entity );

    @Mapping(target = "product", ignore = true)
    OrderSnackItem orderSnackItemEntityToOrderSnackItem(OrderSnackItemEntity orderSnackItemEntity);

    @Mappings({
            @Mapping(target = "category.products", ignore = true),
    })
    ProductEntity toEntity(Product product);

    @Mapping(target = "product", ignore = true)
    OrderSnackItemEntity orderSnackItemEntityToOrderSnackItem(OrderSnackItem orderSnackItem);
    @AfterMapping
    default  void linkEntitiesToProductEntity(Product product, @MappingTarget ProductEntity productEntity) {
        linkCategory(productEntity);
        linkOrderItems(productEntity);
    }

    private void linkOrderItems(ProductEntity productEntity) {
        if(productEntity.getOrderItems() != null)
            productEntity.getOrderItems().forEach(orderItem -> {orderItem.setProduct(productEntity);});
    }

    private void linkCategory(ProductEntity productEntity) {
        List<ProductEntity> newProductsList = productEntity.getCategory().getProducts();
        if (newProductsList == null){
            newProductsList = new ArrayList<ProductEntity>();
        }
        newProductsList.add(productEntity);
        productEntity.getCategory().setProducts(newProductsList);
    }

}
