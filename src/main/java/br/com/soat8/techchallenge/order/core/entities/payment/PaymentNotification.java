package br.com.soat8.techchallenge.order.core.entities.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentNotification {
    @JsonProperty("data")
    private PaymentNotificationData data;
}