package br.com.soat8.techchallenge.core.port.out;

import jakarta.persistence.criteria.Order;

public interface MercadoPagoIntegrationPort {
    String requestQrData(Order order);
}
