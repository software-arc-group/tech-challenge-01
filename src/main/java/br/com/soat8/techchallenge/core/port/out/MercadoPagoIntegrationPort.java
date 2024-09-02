package br.com.soat8.techchallenge.core.port.out;


import br.com.soat8.techchallenge.entities.OrderSnack;

public interface MercadoPagoIntegrationPort {
    String requestQrData(OrderSnack order);
}
