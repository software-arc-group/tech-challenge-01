package br.com.soat8.techchallenge.order.controller;

import br.com.soat8.techchallenge.order.controller.DTO.OrderSnackRequest;
import br.com.soat8.techchallenge.order.core.usecase.OrderSnackUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderSnackUseCase service;

    @PostMapping()
    public ResponseEntity<byte[]> createOrder(@Valid @RequestBody OrderSnackRequest orderSnack){
        return ResponseEntity.ok(service.requestOrder(orderSnack));
    }
}
