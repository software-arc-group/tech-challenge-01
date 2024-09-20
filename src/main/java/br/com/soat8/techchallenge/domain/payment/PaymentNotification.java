package br.com.soat8.techchallenge.domain.payment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentNotification {
    private PaymentNotificationData data;
}

