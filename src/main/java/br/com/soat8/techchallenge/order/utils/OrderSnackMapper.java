package br.com.soat8.techchallenge.order.utils;

import br.com.soat8.techchallenge.order.controller.DTO.OrderSnackRequest;
import br.com.soat8.techchallenge.order.core.entities.OrderSnack;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderSnackMapper {
    OrderSnack toOrderSnack(OrderSnackRequest orderSnackRequest);
}
