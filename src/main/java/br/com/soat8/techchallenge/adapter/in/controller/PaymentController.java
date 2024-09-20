package br.com.soat8.techchallenge.adapter.in.controller;

import br.com.soat8.techchallenge.core.port.in.PaymentUseCase;
import br.com.soat8.techchallenge.domain.payment.PaymentNotification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(PaymentController.BASE_URL)
public class PaymentController {
    public static final String BASE_URL="/lanchonete/payment";
    @Autowired
    public PaymentUseCase paymentUseCase;

    @PostMapping
    public void updatePaymentStatus(@RequestBody PaymentNotification notification) throws JsonProcessingException {
        String not = new ObjectMapper().writeValueAsString(notification);
        log.info("NEW NOTIFICATION ARRIVED: " + not);
        paymentUseCase.updatePaymentStatus(notification);
    }
}
