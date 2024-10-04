package br.com.soat8.techchallenge.order.controller;

import br.com.soat8.techchallenge.order.controller.DTO.OrderSnackRequest;
import br.com.soat8.techchallenge.order.core.entities.OrderSnack;
import br.com.soat8.techchallenge.order.core.entities.enums.OrderProgress;
import br.com.soat8.techchallenge.order.core.usecase.interfaces.CreateOrderSnackUseCase;
import br.com.soat8.techchallenge.order.core.usecase.interfaces.GetOrderSnackProgressUseCase;
import br.com.soat8.techchallenge.order.core.usecase.interfaces.ListOrderSnackUseCase;
import br.com.soat8.techchallenge.order.core.usecase.interfaces.UpdateOrderSnackProgressUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping(OrderController.BASE_URL)
public class OrderController implements OrderSnackProgressApi {

    public static final String BASE_URL = "/lanchonete/order";

    @Autowired
    private CreateOrderSnackUseCase serviceCreateOrderSnackUseCase;

    @Autowired
    private UpdateOrderSnackProgressUseCase serviceUpdateOrderSnackProgressUseCase;

    @Autowired
    private GetOrderSnackProgressUseCase serviceGetOrderSnackProgressUseCase;

    @Autowired
    private final OrderSnackProgressPresenterRest serviceOrderSnackProgressPresenterRest;

    @Autowired
    private final ListOrderSnackUseCase orderSnackUseCase;

    @GetMapping
    public ResponseEntity<List<OrderSnack>> listOrderByProgressAndCustomer(@RequestParam(required = false) OrderProgress progress,
                                                            @RequestParam(required = false) String cpf) {
        List<OrderSnack> orderSnacks = orderSnackUseCase.listOrderSnack(progress, cpf);
        return ResponseEntity.ok(orderSnacks);
    }

    @PostMapping()
    public ResponseEntity<byte[]> createOrder(@RequestBody OrderSnackRequest orderSnack){
        return ResponseEntity.ok(serviceCreateOrderSnackUseCase.requestOrder(orderSnack));
    }

    @PutMapping
    public ResponseEntity<Void> updateOrderSnackProgress(@Valid @RequestBody OrderProgress orderProgress, UUID orderSnackId) {
        serviceUpdateOrderSnackProgressUseCase.updateOrderSnackProgress(orderProgress, orderSnackId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<OrderProgress> getOrderSnackProgress( UUID orderSnackId) {
        OrderProgress result = serviceGetOrderSnackProgressUseCase.getOrderSnackProgress(orderSnackId);
        return ResponseEntity.ok(result);
    }

}
