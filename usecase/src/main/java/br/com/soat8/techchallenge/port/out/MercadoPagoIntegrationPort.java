package br.com.soat8.techchallenge.core_old.port.out;


import br.com.soat8.techchallenge.domain_old.OrderSnack;

public interface MercadoPagoIntegrationPort {
    String requestQrData(OrderSnack order);
}
