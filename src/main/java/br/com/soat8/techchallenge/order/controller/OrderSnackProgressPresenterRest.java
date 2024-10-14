package br.com.soat8.techchallenge.order.controller;

import br.com.soat8.techchallenge.order.core.entities.enums.OrderProgress;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class OrderSnackProgressPresenterRest implements OrderSnackProgressPresenter {

    @Override
    public ResponseEntity<Void> generateOrderSnackProgressResponse(OrderProgress orderProgress, String orderSnackId) {

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(orderSnackId).toUri()).build();

    }
}
