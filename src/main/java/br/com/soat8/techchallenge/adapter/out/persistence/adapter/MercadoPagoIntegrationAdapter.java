package br.com.soat8.techchallenge.adapter.out.persistence.adapter;


import br.com.soat8.techchallenge.adapter.out.persistence.domain.MercadoPagoItem;
import br.com.soat8.techchallenge.adapter.out.persistence.domain.MercadoPagoOrder;
import br.com.soat8.techchallenge.adapter.out.persistence.domain.MercadoPagoOrderData;
import br.com.soat8.techchallenge.adapter.out.persistence.domain.QRCodeData;
import br.com.soat8.techchallenge.core.port.out.MercadoPagoIntegrationPort;
import br.com.soat8.techchallenge.domain.OrderSnack;
import br.com.soat8.techchallenge.domain.payment.OrderSnackPaymentStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
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
    @Value("${integration.mercadopago.notificationUrl}")
    private String notificationUrl;

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
           log.info("Requesting QRCode data to MercadoPago with order: " + new ObjectMapper().writeValueAsString(mercadoPagoOrder));
           ResponseEntity<QRCodeData> response = restTemplate.postForEntity(fullUrl, mercadoPagoOrder, QRCodeData.class);
           log.info("response: " + new ObjectMapper().writeValueAsString(response.getBody()));

           return Objects.requireNonNull(response.getBody());
       }catch (Exception ex){
           log.info("Error requesting QRCode data to MercadoPago: " + ex.getMessage());
           throw new RuntimeException(ex.getMessage());
       }
    }

    @Override
    public OrderSnackPaymentStatus getOrderData(String paymentId) {
        String fullUrl = orderDataUrl + "/" + paymentId + "?access_token=" + accessToken;
        try{
            log.info("Requesting order data to MercadoPago with paymentId: " + paymentId);
            ResponseEntity<MercadoPagoOrderData> response = restTemplate.getForEntity(fullUrl, MercadoPagoOrderData.class);
            MercadoPagoOrderData mercadoPagoOrderData = Objects.requireNonNull(response.getBody());
            log.info("response: " + new ObjectMapper().writeValueAsString(mercadoPagoOrderData));
            return new OrderSnackPaymentStatus(UUID.fromString(mercadoPagoOrderData.getId()), mercadoPagoOrderData.getStatus());

        }catch (Exception ex){
            log.info("Error requesting order data to MercadoPago: " + ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    public MercadoPagoOrder convert(OrderSnack orderSnack) {
        MercadoPagoOrder mercadoPagoOrder = new MercadoPagoOrder();
        mercadoPagoOrder.setDescription(DEFAULT_DESCRIPTION);
        mercadoPagoOrder.setTitle(DEFAULT_DESCRIPTION);
        mercadoPagoOrder.setExternalReference(externalReference);
        mercadoPagoOrder.setTotalAmount(orderSnack.getTotalPrice());
        mercadoPagoOrder.setNotificationUrl(notificationUrl);

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
