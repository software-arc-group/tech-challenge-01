package br.com.soat8.techchallenge.order.core.usecases.in;

import br.com.soat8.techchallenge.domain.payment.PaymentNotification;

public interface PaymentUseCase {
    void updatePaymentStatus(PaymentNotification notification);
}
