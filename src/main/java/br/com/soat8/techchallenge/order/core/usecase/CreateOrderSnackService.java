package br.com.soat8.techchallenge.order.core.usecase;

import br.com.soat8.techchallenge.client.core.entities.Customer;
import br.com.soat8.techchallenge.client.core.usecase.interfaces.SearchCustomerIdUseCase;
import br.com.soat8.techchallenge.item.core.usecase.GetOrderSnackItemByIdUseCase;
import br.com.soat8.techchallenge.order.controller.DTO.OrderItemRequest;
import br.com.soat8.techchallenge.order.controller.DTO.OrderSnackRequest;
import br.com.soat8.techchallenge.order.adapters.external.MercadoPagoIntegrationPort;
import br.com.soat8.techchallenge.order.adapters.repository.OrderSnackPort;
import br.com.soat8.techchallenge.order.controller.QRCodePort;
import br.com.soat8.techchallenge.order.core.entities.OrderSnack;
import br.com.soat8.techchallenge.order.core.entities.OrderSnackItem;
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
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CreateOrderSnackService implements CreateOrderSnackUseCase {

    @Autowired
    private final MercadoPagoIntegrationPort mercadoPagoIntegrationPort;
    @Autowired
    private final QRCodePort qrCodePort;
    @Autowired
    private final OrderSnackMapper orderSnackMapper;
    @Autowired
    private final SearchCustomerIdUseCase searchCustomerIdUseCase;
    @Autowired
    private final GetOrderSnackItemByIdUseCase getOrderSnackItemByIdUseCase;
    @Autowired
    private final OrderSnackPort orderSnackPort;
    @Autowired
    private final GetProductUseCase getProductUseCase;



    @Override
    public byte[] requestOrder(OrderSnackRequest  orderSnackRequest) {

        Customer customer = searchCustomerIdUseCase.searchById(orderSnackRequest.getCustomerId());
        List<Product> items = getListOfItems(orderSnackRequest.getItems());

        OrderSnack orderSnack = OrderSnack.builder().items(items).customer(customer).createdAt(LocalDateTime.now()).build();
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

    private List<Product> getListOfItems(List<OrderItemRequest> list) {
        return list.stream().
            map(item -> getProductUseCase.getProduct(item.getProductId()))
                .collect(Collectors.toList());
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

}
