package br.com.soat8.techchallenge.order.controller;

import br.com.soat8.techchallenge.order.core.entities.enums.OrderProgress;
import org.springframework.http.ResponseEntity;

public interface OrderSnackProgressPresenter {

    ResponseEntity<Void> generateOrderSnackProgressResponse(OrderProgress orderProgress, String orderSnackId);

}
