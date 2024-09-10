package br.com.soat8.techchallenge.adapter.out.persistence.domain;

import lombok.Data;

@Data
public class MercadoPagoOrderData {
    private String id;
    private String status;
    private String statusDetail;
    private String externalReference;
    private String paymentType;
    private String paymentMethodId;
    private String paymentMethodReferenceId;
    private String transactionAmount;
    private String totalPaidAmount;
}
