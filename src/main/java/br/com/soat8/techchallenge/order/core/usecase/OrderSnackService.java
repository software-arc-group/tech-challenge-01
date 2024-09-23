package br.com.soat8.techchallenge.order.core.usecase;

import br.com.soat8.techchallenge.order.controller.DTO.OrderProgressRequest;
import br.com.soat8.techchallenge.order.controller.DTO.OrderSnackRequest;
import br.com.soat8.techchallenge.order.core.entities.enums.OrderProgress;
import br.com.soat8.techchallenge.order.adapters.external.MercadoPagoIntegrationPort;
import br.com.soat8.techchallenge.order.adapters.repository.OrderSnackPort;
import br.com.soat8.techchallenge.order.controller.QRCodePort;
import br.com.soat8.techchallenge.order.core.entities.OrderSnack;
import br.com.soat8.techchallenge.order.core.entities.OrderSnackItem;
import br.com.soat8.techchallenge.order.utils.OrderProgressMapper;
import br.com.soat8.techchallenge.order.utils.OrderSnackMapper;
import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
public class OrderSnackService implements OrderSnackUseCase {

    @Autowired
    private final MercadoPagoIntegrationPort mercadoPagoIntegrationPort;
    @Autowired
    private final QRCodePort qrCodePort;
    @Autowired
    private final OrderSnackPort orderSnackPort;
    @Autowired
    private final OrderProgressMapper progressMapper;
    @Autowired
    private final OrderSnackMapper orderSnackMapper;


    @Override
    public byte[] requestOrder(OrderSnackRequest orderSnackRequest) {
        OrderSnack orderSnack = orderSnackMapper.toOrderSnack(orderSnackRequest);
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
    public List<OrderSnack> listOrderSnack(OrderProgressRequest progress, String cpf) {
        OrderProgress orderProgress = progressMapper.toOrderProgress(progress);
        return orderSnackPort.listOrderSnack(orderProgress, cpf);
    }
}
