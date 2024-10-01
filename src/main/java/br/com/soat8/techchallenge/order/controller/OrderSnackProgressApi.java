package br.com.soat8.techchallenge.order.controller;

import br.com.soat8.techchallenge.order.core.entities.enums.OrderProgress;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface OrderSnackProgressApi {

    ResponseEntity<Void> updateOrderSnackProgress (OrderProgress orderProgress, UUID orderSnackId);

    ResponseEntity<OrderProgress> getOrderSnackProgress (UUID orderSnackId);

}
