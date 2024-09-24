package br.com.soat8.techchallenge.adapter.in.controller;

import br.com.soat8.techchallenge.core.port.in.PaymentUseCase;
import br.com.soat8.techchallenge.domain.payment.PaymentNotification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(PaymentController.BASE_URL)
public class PaymentController {
    public static final String BASE_URL="/lanchonete/payment";
    @Autowired
    public PaymentUseCase paymentUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void updatePaymentStatus(@RequestBody PaymentNotification notification) throws JsonProcessingException {
        String not = new ObjectMapper().writeValueAsString(notification);
        if(notification.getData().getId().isBlank()){
            log.error("ID NOT FOUND IN NOTIFICATION: " + not);
            return;
        }
        log.info("NEW NOTIFICATION ARRIVED: " + not);
        paymentUseCase.updatePaymentStatus(notification);
    }
}
