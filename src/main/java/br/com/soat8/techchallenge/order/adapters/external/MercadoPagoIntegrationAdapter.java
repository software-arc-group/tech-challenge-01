package br.com.soat8.techchallenge.order.adapters.external;


import br.com.soat8.techchallenge.order.core.entities.mercadopago.MercadoPagoItem;
import br.com.soat8.techchallenge.order.core.entities.mercadopago.MercadoPagoOrder;
import br.com.soat8.techchallenge.order.core.entities.mercadopago.QRCodeData;
import br.com.soat8.techchallenge.order.core.entities.OrderSnack;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Collectors;


@Component
public class MercadoPagoIntegrationAdapter implements MercadoPagoIntegrationPort {
    @Value("${integration.mercadopago.url}")
    private  String url;
    @Value("${integration.mercadopago.path}")
    private  String path;
    @Value("${integration.mercadopago.accesstoken}")
    private  String accessToken;
    @Value("${integration.mercadopago.externalReference}")
    private  String externalReference;

    private static final String DEFAULT_DESCRIPTION = "Order Snack";

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public String requestQrData(OrderSnack order) {
        String fullUrl = url + "/" +  path + "?access_token=" + accessToken;
        MercadoPagoOrder mercadoPagoOrder = convert(order);
       try{
           ResponseEntity<QRCodeData> response = restTemplate.postForEntity(fullUrl, mercadoPagoOrder, QRCodeData.class);

           return Objects.requireNonNull(response.getBody()).getQrData();
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
                        item.getProduct().getProductId().toString(),
                        item.getProduct().getPrice(),
                        item.getQuantity(),
                        "unit",
                        item.getAmount()
                        ))
                .collect(Collectors.toList()));

        return mercadoPagoOrder;
    }
}
