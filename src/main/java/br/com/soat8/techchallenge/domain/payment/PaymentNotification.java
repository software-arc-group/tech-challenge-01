package br.com.soat8.techchallenge.domain.payment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentNotification {
    @JsonProperty("data")
    private PaymentNotificationData data;
}

