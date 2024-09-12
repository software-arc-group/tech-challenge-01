package br.com.soat8.techchallenge.order.core.usecase;

import br.com.soat8.techchallenge.order.core.entities.enums.OrderProgress;
import br.com.soat8.techchallenge.order.adapters.external.MercadoPagoIntegrationPort;
import br.com.soat8.techchallenge.order.adapters.repository.OrderSnackPort;
import br.com.soat8.techchallenge.order.controller.QRCodePort;
import br.com.soat8.techchallenge.order.core.entities.OrderSnack;
import br.com.soat8.techchallenge.entities.OrderSnackItem;
import com.google.zxing.WriterException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

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
        orderSnack.setTotalPrice(calculateTotalPrice(orderSnack));
        String qrData = mercadoPagoIntegrationPort.requestQrData(orderSnack);
        try {
            byte[] qrCodeImg = qrCodePort.generateQRCodeImage(qrData, 250, 250);
            orderSnackPort.saveOrderSnack(orderSnack);
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
