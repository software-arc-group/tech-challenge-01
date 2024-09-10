package br.com.soat8.techchallenge.adapter.out.persistence.adapter;


import br.com.soat8.techchallenge.adapter.out.persistence.domain.MercadoPagoItem;
import br.com.soat8.techchallenge.adapter.out.persistence.domain.MercadoPagoOrder;
import br.com.soat8.techchallenge.adapter.out.persistence.domain.MercadoPagoOrderData;
import br.com.soat8.techchallenge.adapter.out.persistence.domain.QRCodeData;
import br.com.soat8.techchallenge.core.port.out.MercadoPagoIntegrationPort;
import br.com.soat8.techchallenge.domain.OrderSnack;
import br.com.soat8.techchallenge.domain.payment.OrderSnackPaymentStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class MercadoPagoIntegrationAdapter implements MercadoPagoIntegrationPort {
    @Value("${integration.mercadopago.url}")
    private String url;
    @Value("${integration.mercadopago.path}")
    private String path;
    @Value("${integration.mercadopago.accesstoken}")
    private String accessToken;
    @Value("${integration.mercadopago.externalReference}")
    private String externalReference;

    @Value("${integration.mercadopago.orderDataUrl}")
    private String orderDataUrl;

    private static final String DEFAULT_DESCRIPTION = "Order Snack";

    private RestTemplate restTemplate;

    public MercadoPagoIntegrationAdapter(){
        restTemplate = new RestTemplate();
    }

    @Override
    public QRCodeData requestQrData(OrderSnack order) {
        String fullUrl = url + "/" +  path + "?access_token=" + accessToken;
        MercadoPagoOrder mercadoPagoOrder = convert(order);
       try{
           ResponseEntity<QRCodeData> response = restTemplate.postForEntity(fullUrl, mercadoPagoOrder, QRCodeData.class);

           return Objects.requireNonNull(response.getBody());
       }catch (Exception ex){
           throw new RuntimeException(ex.getMessage());
       }
    }

    @Override
    public OrderSnackPaymentStatus getOrderData(String paymentId) {
        String fullUrl = orderDataUrl + "/" + paymentId + "?access_token=" + accessToken;
        try{
            ResponseEntity<MercadoPagoOrderData> response = restTemplate.getForEntity(fullUrl, MercadoPagoOrderData.class);
            MercadoPagoOrderData mercadoPagoOrderData = Objects.requireNonNull(response.getBody());

            return new OrderSnackPaymentStatus(UUID.fromString(mercadoPagoOrderData.getId()), mercadoPagoOrderData.getStatus());

        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public MercadoPagoOrder convert(OrderSnack orderSnack) {
        MercadoPagoOrder mercadoPagoOrder = new MercadoPagoOrder();
        mercadoPagoOrder.setDescription(DEFAULT_DESCRIPTION);
        mercadoPagoOrder.setTitle(DEFAULT_DESCRIPTION);
        mercadoPagoOrder.setExternalReference(externalReference);
        mercadoPagoOrder.setTotalAmount(orderSnack.getTotalPrice());

        mercadoPagoOrder.setItems(orderSnack.getItems().stream()
                .map(item -> new MercadoPagoItem(
                        item.getProductId().toString(),
                        item.getPrice(),
                        item.getQuantity(),
                        "unit",
                        item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))
                        ))
                .collect(Collectors.toList()));

        return mercadoPagoOrder;
    }
}
