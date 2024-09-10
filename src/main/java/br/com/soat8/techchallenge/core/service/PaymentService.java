package br.com.soat8.techchallenge.core.service;

import br.com.soat8.techchallenge.adapter.out.persistence.domain.MercadoPagoOrderData;
import br.com.soat8.techchallenge.adapter.out.persistence.entity.OrderSnackEntity;
import br.com.soat8.techchallenge.adapter.out.persistence.entity.enums.OrderProgress;
import br.com.soat8.techchallenge.adapter.out.persistence.entity.enums.PaymentProgress;
import br.com.soat8.techchallenge.adapter.out.persistence.retository.OrderSnackRepository;
import br.com.soat8.techchallenge.core.port.in.PaymentUseCase;
import br.com.soat8.techchallenge.core.port.out.MercadoPagoIntegrationPort;
import br.com.soat8.techchallenge.domain.payment.OrderSnackPaymentStatus;
import br.com.soat8.techchallenge.domain.payment.PaymentNotification;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class PaymentService implements PaymentUseCase {
    @Autowired
    MercadoPagoIntegrationPort mercadoPagoIntegrationPort;
    @Autowired
    OrderSnackRepository orderSnackRepository;

    public void updatePaymentStatus(PaymentNotification notification) {
        try{
            OrderSnackPaymentStatus mercadoPagoOrderData = mercadoPagoIntegrationPort.getOrderData(notification.getData().getId());
            OrderSnackEntity orderSnackEntity = orderSnackRepository.findByExternalOrderId(mercadoPagoOrderData.getExternalOrderId());
            orderSnackEntity.setPaymentProgress(generatePaymentStatus(mercadoPagoOrderData.getPaymentStatus()));
            orderSnackRepository.save(orderSnackEntity);
        }catch (Exception e){
            log.error("Error updating payment status: " + e.getMessage());
            throw new RuntimeException("Error updating payment status: " + e.getMessage());
        }
    }


    private PaymentProgress generatePaymentStatus(String status){
        return switch (status) {
            case "closed" -> PaymentProgress.APPROVED;
            case "opened" -> PaymentProgress.OPENED;
            case "expired" -> PaymentProgress.EXPIRED;
            default -> throw new UnsupportedOperationException("Payment stauts" + status + " not supported, choose between: closed, opened or expired");
        };
    }
}
