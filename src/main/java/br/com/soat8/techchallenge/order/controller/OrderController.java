package br.com.soat8.techchallenge.order.controller;

import br.com.soat8.techchallenge.client.controller.CustomerController;
import br.com.soat8.techchallenge.order.controller.DTO.OrderSnackRequest;
import br.com.soat8.techchallenge.order.core.entities.enums.OrderProgress;
import br.com.soat8.techchallenge.order.core.usecase.interfaces.CreateOrderSnackUseCase;
import br.com.soat8.techchallenge.order.core.usecase.interfaces.SearchOrderSnackProgressUseCase;
import br.com.soat8.techchallenge.order.core.usecase.interfaces.UpdateOrderSnackProgressUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(CustomerController.BASE_URL)
public class OrderController implements OrderSnackProgressApi {

    public static final String BASE_URL = "/lanchonete/order";

    @Autowired
    private CreateOrderSnackUseCase serviceCreateOrderSnackUseCase;

    @Autowired
    private UpdateOrderSnackProgressUseCase serviceUpdateOrderSnackProgressUseCase;

    @Autowired
    private SearchOrderSnackProgressUseCase serviceSearchOrderSnackProgressUseCase;

    @Autowired
    private final OrderSnackProgressPresenterRest serviceOrderSnackProgressPresenterRest;

    @PostMapping()
    public ResponseEntity<byte[]> createOrder(@RequestBody OrderSnackRequest orderSnack){
        return ResponseEntity.ok(serviceCreateOrderSnackUseCase.requestOrder(orderSnack));
    }

    @PostMapping
    public ResponseEntity<Void> updateOrderSnackProgress(@Valid @RequestBody OrderProgress orderProgress, String orderSnackId) {
        OrderProgress createdCustomer = serviceUpdateOrderSnackProgressUseCase.updateOrderSnackProgress(orderProgress, orderSnackId);
        return serviceOrderSnackProgressUseCase.generateOrderSnackProgressResponse(createdCustomer);

        //return ResponseEntity.ok(serviceUpdateOrderSnackProgressUseCase.updateOrderSnackProgress(orderProgress, orderSnackId));

    }

    @GetMapping
    public ResponseEntity<OrderProgress> searchOrderSnackProgress( String orderSnackId) {
        OrderProgress result = serviceSearchOrderSnackProgressUseCase.searchOrderSnackProgress(orderSnackId);
        return ResponseEntity.ok(result);
    }

}
