package br.com.soat8.techchallenge.core.service;

import br.com.soat8.techchallenge.adapter.out.persistence.domain.QRCodeData;
import br.com.soat8.techchallenge.adapter.out.persistence.entity.enums.OrderProgress;
import br.com.soat8.techchallenge.core.port.in.OrderSnackUseCase;
import br.com.soat8.techchallenge.core.port.out.MercadoPagoIntegrationPort;
import br.com.soat8.techchallenge.core.port.out.OrderSnackPort;
import br.com.soat8.techchallenge.core.port.out.QRCodePort;
import br.com.soat8.techchallenge.domain.OrderSnack;
import br.com.soat8.techchallenge.domain.OrderSnackItem;
import com.google.zxing.WriterException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class OrderSnackService implements OrderSnackUseCase {

    private final MercadoPagoIntegrationPort mercadoPagoIntegrationPort;
    private final QRCodePort qrCodePort;
    private final OrderSnackPort orderSnackPort;


    public OrderSnackService(MercadoPagoIntegrationPort mercadoPagoIntegrationPort,
                             QRCodePort qrCodePort,
                             OrderSnackPort orderSnackPort) {
        this.mercadoPagoIntegrationPort = mercadoPagoIntegrationPort;
        this.qrCodePort = qrCodePort;
        this.orderSnackPort = orderSnackPort;
    }

    @Override
    public byte[] requestOrder(OrderSnack orderSnack) {
        UUID externalReference = UUID.randomUUID();
        orderSnack.setTotalPrice(calculateTotalPrice(orderSnack));
        QRCodeData qrData = mercadoPagoIntegrationPort.requestQrData(orderSnack, externalReference);
        try {
            byte[] qrCodeImg = qrCodePort.generateQRCodeImage(qrData.getQrData(), 250, 250);
            orderSnackPort.saveOrderSnack(orderSnack, externalReference);
            return qrCodeImg;

        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private BigDecimal calculateTotalPrice(OrderSnack orderSnack) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderSnackItem item : orderSnack.getItems()) {
            BigDecimal itemTotal = getTotalPrice(item);
            totalPrice = totalPrice.add(itemTotal);
        }
        return totalPrice;
    }

    public BigDecimal getTotalPrice(OrderSnackItem orderSnackItem) {
        BigDecimal quantityAsBigDecimal = BigDecimal.valueOf(orderSnackItem.getQuantity());
        return orderSnackItem.getPrice().multiply(quantityAsBigDecimal);
    }

    @Override
    public List<OrderSnack> listOrderSnack(OrderProgress progress, String cpf) {
        return orderSnackPort.listOrderSnack(progress, cpf);
    }
}
