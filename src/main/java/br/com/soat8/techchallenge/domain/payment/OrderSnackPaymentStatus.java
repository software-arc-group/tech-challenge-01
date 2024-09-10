package br.com.soat8.techchallenge.domain.payment;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@Data
@AllArgsConstructor
public class OrderSnackPaymentStatus {
    public UUID externalOrderId;
    public String paymentStatus;
}
