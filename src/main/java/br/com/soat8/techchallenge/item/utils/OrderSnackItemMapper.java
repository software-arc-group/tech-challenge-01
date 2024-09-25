package br.com.soat8.techchallenge.item.utils;

import br.com.soat8.techchallenge.item.adapter.repository.entities.OrderSnackItemEntity;
import br.com.soat8.techchallenge.order.core.entities.OrderSnackItem;
import org.mapstruct.Mapper;

@Mapper
public interface OrderSnackItemMapper {
    OrderSnackItem toOrderSnackItem(OrderSnackItemEntity entity);
    OrderSnackItemEntity toEntity(OrderSnackItem entity);
}
