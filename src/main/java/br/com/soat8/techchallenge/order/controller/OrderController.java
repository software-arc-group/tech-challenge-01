package br.com.soat8.techchallenge.order.controller;

import br.com.soat8.techchallenge.client.controller.DTO.CustomerRequest;
import br.com.soat8.techchallenge.client.core.entities.Customer;
import br.com.soat8.techchallenge.order.controller.DTO.OrderProgressRequest;
import br.com.soat8.techchallenge.order.controller.DTO.OrderSnackRequest;
import br.com.soat8.techchallenge.order.core.entities.enums.OrderProgress;
import br.com.soat8.techchallenge.order.core.usecase.interfaces.CreateOrderSnackUseCase;
import br.com.soat8.techchallenge.order.core.usecase.interfaces.OrderSnackProgressUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private CreateOrderSnackUseCase serviceCreateOrderSnackUseCase;

    @Autowired
    private OrderSnackProgressUseCase serviceOrderSnackProgressUseCase;

    @PostMapping()
    public ResponseEntity<byte[]> createOrder(@RequestBody OrderSnackRequest orderSnack){
        return ResponseEntity.ok(serviceCreateOrderSnackUseCase.requestOrder(orderSnack));
    }

    @PostMapping
    public ResponseEntity<Void> updateOrderSnackProgress(@Valid @RequestBody OrderProgressRequest orderProgressRequest) {
        OrderProgress createdCustomer = serviceOrderSnackProgressUseCase.updateOrderSnackProgress(orderProgressRequest);
        return serviceOrderSnackProgressUseCase.generateCustomerCreatedResponse(createdCustomer);
    }

    @GetMapping
    public ResponseEntity<OrderProgress> searchOrderSnackProgress( String orderSnackId) {
        OrderProgress result = serviceOrderSnackProgressUseCase.searchOrderSnackProgress(orderSnackId);
        return ResponseEntity.ok(result);
    }

}
