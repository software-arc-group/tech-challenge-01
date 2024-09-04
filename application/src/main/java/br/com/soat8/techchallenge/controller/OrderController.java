package br.com.soat8.techchallenge.controller;

import br.com.soat8.techchallenge.core_old.port.in.OrderSnackUseCase;
import br.com.soat8.techchallenge.domain_old.OrderSnack;
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
    public ResponseEntity<byte[]> createOrder(@Valid @RequestBody OrderSnack orderSnack){
        return ResponseEntity.ok(service.requestOrder(orderSnack));
    }
}
