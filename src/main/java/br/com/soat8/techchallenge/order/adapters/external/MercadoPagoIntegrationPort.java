package br.com.soat8.techchallenge.order.adapters.external;


import br.com.soat8.techchallenge.order.core.entities.OrderSnack;
import br.com.soat8.techchallenge.order.core.entities.mercadopago.QRCodeData;
import br.com.soat8.techchallenge.order.core.entities.payment.OrderSnackPaymentStatus;

import java.util.UUID;

public interface MercadoPagoIntegrationPort {
    QRCodeData requestQrData(OrderSnack order, UUID externalReference);
    OrderSnackPaymentStatus getOrderData(String paymentId);
}
