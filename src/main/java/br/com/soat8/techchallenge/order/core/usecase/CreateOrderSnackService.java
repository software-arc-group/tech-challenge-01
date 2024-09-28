package br.com.soat8.techchallenge.order.core.usecase;

import br.com.soat8.techchallenge.client.core.entities.Customer;
import br.com.soat8.techchallenge.client.core.usecase.interfaces.SearchCustomerIdUseCase;
import br.com.soat8.techchallenge.order.controller.DTO.OrderItemRequest;
import br.com.soat8.techchallenge.order.controller.DTO.OrderSnackRequest;
import br.com.soat8.techchallenge.order.adapters.external.MercadoPagoIntegrationPort;
import br.com.soat8.techchallenge.order.adapters.repository.OrderSnackPort;
import br.com.soat8.techchallenge.order.controller.QRCodePort;
import br.com.soat8.techchallenge.order.core.entities.OrderSnack;
import br.com.soat8.techchallenge.order.core.entities.OrderSnackItem;
import br.com.soat8.techchallenge.order.core.entities.mercadopago.QRCodeData;
import br.com.soat8.techchallenge.order.core.usecase.interfaces.CreateOrderSnackUseCase;
import br.com.soat8.techchallenge.order.utils.OrderSnackMapper;
import br.com.soat8.techchallenge.product.core.entities.Product;
import br.com.soat8.techchallenge.product.core.usecase.interfaces.GetProductUseCase;
import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CreateOrderSnackService implements CreateOrderSnackUseCase {

    @Autowired
    private final MercadoPagoIntegrationPort mercadoPagoIntegrationPort;
    @Autowired
    private final QRCodePort qrCodePort;
    @Autowired
    private final SearchCustomerIdUseCase searchCustomerIdUseCase;
    @Autowired
    private final OrderSnackPort orderSnackPort;
    @Autowired
    private final GetProductUseCase getProductUseCase;



    @Override
    public byte[] requestOrder(OrderSnackRequest  orderSnackRequest) {
        UUID externalReference = UUID.randomUUID();

        Customer customer = searchCustomerIdUseCase.searchById(orderSnackRequest.getCustomerId());
        List<OrderSnackItem> listOfOrderItems = generateListOfOrderItems(orderSnackRequest.getItems());
        OrderSnack orderSnack = new OrderSnack();
        orderSnack.setItems(listOfOrderItems);
        orderSnack.setCustomer(customer);
        QRCodeData qrData = mercadoPagoIntegrationPort.requestQrData(orderSnack, externalReference);
        try {
            byte[] qrCodeImg = qrCodePort.generateQRCodeImage(qrData.getQrData(), 250, 250);
            orderSnackPort.saveOrderSnack(orderSnack, externalReference);
            return qrCodeImg;
        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<OrderSnackItem> generateListOfOrderItems(List<OrderItemRequest> list) {
        return list.stream().
            map(item -> generateOrderItem(item.getProductId(), item.getQtd()))
                .collect(Collectors.toList());
    }

    private OrderSnackItem generateOrderItem(String productId, int qtd){
        Product product = getProductUseCase.getProduct(productId);
        BigDecimal quantityAsBigDecimal = BigDecimal.valueOf(qtd);
        BigDecimal fullValue = product.getPrice().multiply(quantityAsBigDecimal);
        return new OrderSnackItem(null, null , fullValue, product,qtd );
    }



}
