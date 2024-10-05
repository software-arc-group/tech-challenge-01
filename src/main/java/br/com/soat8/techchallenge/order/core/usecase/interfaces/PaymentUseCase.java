package br.com.soat8.techchallenge.order.core.usecase.interfaces;

import br.com.soat8.techchallenge.order.core.entities.payment.PaymentNotification;

public interface PaymentUseCase {
    void updatePaymentStatus(PaymentNotification notification);
}