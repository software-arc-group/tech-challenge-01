package br.com.soat8.techchallenge.core.port.in;

import br.com.soat8.techchallenge.domain.payment.PaymentNotification;

public interface PaymentUseCase {
    void updatePaymentStatus(PaymentNotification notification);
}
