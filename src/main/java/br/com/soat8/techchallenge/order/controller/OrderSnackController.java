package br.com.soat8.techchallenge.order.controller;

import br.com.soat8.techchallenge.order.controller.DTO.OrderProgressRequest;
import br.com.soat8.techchallenge.order.core.entities.OrderSnack;
import br.com.soat8.techchallenge.order.core.usecase.interfaces.ListOrderSnackUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(OrderSnackController.BASE_URL)
public class OrderSnackController {
    public static final String BASE_URL = "/lanchonete/order";

    @Autowired
    private final ListOrderSnackUseCase orderSnackUseCase;


    @GetMapping
    public ResponseEntity<List<OrderSnack>> cadastrarCliente( @RequestParam(required = false) OrderProgressRequest progress,
                                                              @RequestParam(required = false) String cpf) {
        List<OrderSnack> orderSnacks = orderSnackUseCase.listOrderSnack(progress, cpf);
        return ResponseEntity.ok(orderSnacks);
    }


}
