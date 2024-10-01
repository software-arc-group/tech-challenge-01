package br.com.soat8.techchallenge.order.controller;

import br.com.soat8.techchallenge.order.core.entities.enums.OrderProgress;
import org.springframework.http.ResponseEntity;

public interface OrderSnackProgressApi {

    ResponseEntity<Void> updateOrderSnackProgress (OrderProgress orderProgress, String orderSnackId);

    ResponseEntity<OrderProgress> getOrderSnackProgress (String orderSnackId);

}
