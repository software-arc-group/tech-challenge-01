package br.com.soat8.techchallenge.adapter.out.persistence.adapter;


import br.com.soat8.techchallenge.adapter.out.persistence.domain.MercadoPagoCashOut;
import br.com.soat8.techchallenge.adapter.out.persistence.domain.MercadoPagoItem;
import br.com.soat8.techchallenge.adapter.out.persistence.domain.MercadoPagoOrder;
import br.com.soat8.techchallenge.adapter.out.persistence.domain.QRCodeData;
import br.com.soat8.techchallenge.core.port.out.MercadoPagoIntegrationPort;
import br.com.soat8.techchallenge.domain.OrderSnack;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.stream.Collectors;

public class MercadoPagoIntegrationAdapter implements MercadoPagoIntegrationPort {
    @Value("integration.mercadopago.url")
    private String url;
    @Value("integration.mercadopago.path")
    private String path;
    @Value("integration.mercadopago.accesstoken")
    private String accessToken;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public String requestQrData(Order order) {
        String fullUrl = url + "/" +  path + "?access_token=" + accessToken;

        ResponseEntity<QRCodeData> response = restTemplate.postForEntity(fullUrl, order, QRCodeData.class);

        return Objects.requireNonNull(response.getBody()).getQrData();
    }

    public MercadoPagoOrder convert(OrderSnack orderSnack) {
        MercadoPagoOrder mercadoPagoOrder = new MercadoPagoOrder();

        mercadoPagoOrder.setExternalReference("LANCHONETE1");
        mercadoPagoOrder.setItems(orderSnack.getItems().stream()
                .map(item -> new MercadoPagoItem(
                        item.getProduct().getProductId().toString(),
                        item.getProduct().getName(),
                        item.getProduct().getPrice(),
                        item.getAmount(), // Assuming quantity is always 1
                        "unit"
                        ))
                .collect(Collectors.toList()));
        mercadoPagoOrder.setTitle("Product order");
        mercadoPagoOrder.setTotalAmount(orderSnack.getTotalAmount());
        mercadoPagoOrder.setCashOut(new MercadoPagoCashOut(orderSnack.getTotalAmount()));

        return mercadoPagoOrder;
    }
}
