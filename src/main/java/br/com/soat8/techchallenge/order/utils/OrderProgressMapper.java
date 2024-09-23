package br.com.soat8.techchallenge.order.utils;

import br.com.soat8.techchallenge.order.controller.DTO.OrderProgressRequest;
import br.com.soat8.techchallenge.order.core.entities.enums.OrderProgress;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderProgressMapper {
    OrderProgress toOrderProgress(OrderProgressRequest orderProgressRequest);
}