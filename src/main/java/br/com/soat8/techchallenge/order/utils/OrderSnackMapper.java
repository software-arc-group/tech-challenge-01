package br.com.soat8.techchallenge.order.utils;

import br.com.soat8.techchallenge.order.adapters.repository.entities.OrderSnackEntity;
import br.com.soat8.techchallenge.order.adapters.repository.entities.OrderSnackItemEntity;
import br.com.soat8.techchallenge.order.core.entities.OrderSnack;
import br.com.soat8.techchallenge.order.core.entities.OrderSnackItem;
import br.com.soat8.techchallenge.product.adapters.repository.entities.ProductEntity;
import br.com.soat8.techchallenge.product.core.entities.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderSnackMapper {
    @Mappings({
       @Mapping(source = "customer.id" , target = "customer.customerId"),
       @Mapping(source = "items", target = "orderSnackItems"),
    })
    OrderSnackEntity toEntity(OrderSnack orderSnack);

    @Mappings({
        @Mapping(target = "category", ignore = true),
        @Mapping(target = "orderItems", ignore = true)
    })
    ProductEntity productToProductEntity(Product product);
    @AfterMapping
    default  void linkItemEntityBackToOrderEntity(OrderSnack orderSnack, @MappingTarget OrderSnackEntity orderSnackEntity){
        List<OrderSnackItemEntity> items = orderSnackEntity.getOrderSnackItems();
        if(items != null){
            for(OrderSnackItemEntity item: items){
                item.setOrderSnack(orderSnackEntity);
            }
        }
    }

    @Mappings({
            @Mapping(source = "customer.customerId" , target = "customer.id"),
            @Mapping(source = "orderSnackItems", target = "items")
    })
    OrderSnack toOrderSnack(OrderSnackEntity orderSnackEntity);

    @Mapping(target = "orderSnack", ignore = true )
    OrderSnackItem orderSnackItemEntityToOrderSnackItem(OrderSnackItemEntity orderSnackItemEntity);

    @Mappings({
        @Mapping(target = "orderItems", ignore = true),
        @Mapping(target = "category", ignore = true )
    })
    Product productEntityToProduct(ProductEntity productEntity);
}
