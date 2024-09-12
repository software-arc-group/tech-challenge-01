package br.com.soat8.techchallenge.order.adapters.external;


import br.com.soat8.techchallenge.order.core.entities.OrderSnack;

public interface MercadoPagoIntegrationPort {
    String requestQrData(OrderSnack order);
}
