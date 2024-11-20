package br.com.soat8.techchallenge.order.core.validator;

import br.com.soat8.techchallenge.order.controller.DTO.OrderSnackRequest;
import br.com.soat8.techchallenge.order.core.enums.PaymentType;
import br.com.soat8.techchallenge.order.core.validator.impl.ValidationBoleto;
import br.com.soat8.techchallenge.order.core.validator.impl.ValidationCreditCard;

import java.util.HashMap;
import java.util.Map;

public class ValidationPaymentContext {
    private final Map<PaymentType, ValidationPaymentStrategy> strategies;

    public ValidationPaymentContext() {
        strategies = new HashMap<>();
        strategies.put(PaymentType.BOLETO, new ValidationBoleto());
        strategies.put(PaymentType.CREDIT_CARD, new ValidationCreditCard());
    }

    public void validate(OrderSnackRequest orderSnackRequest) {
        ValidationPaymentStrategy strategy = strategies.get(orderSnackRequest.getPaymentType());
        if (strategy != null) {
            strategy.validate(orderSnackRequest);
        } else {
            throw new IllegalArgumentException("No validation strategy found for: " + orderSnackRequest.getPaymentType());
        }
    }
}
