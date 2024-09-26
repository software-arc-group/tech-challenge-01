package br.com.soat8.techchallenge.core.port.out;


import br.com.soat8.techchallenge.adapter.out.persistence.domain.QRCodeData;
import br.com.soat8.techchallenge.domain.OrderSnack;
import br.com.soat8.techchallenge.domain.payment.OrderSnackPaymentStatus;

import java.util.UUID;

public interface MercadoPagoIntegrationPort {
    QRCodeData requestQrData(OrderSnack order, UUID externalReference);
    OrderSnackPaymentStatus getOrderData(String paymentId);
}
