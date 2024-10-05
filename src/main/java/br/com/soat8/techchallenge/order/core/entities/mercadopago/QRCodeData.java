package br.com.soat8.techchallenge.order.core.entities.mercadopago;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QRCodeData {
    @JsonProperty("in_store_order_id")
    private String inStoreData;
    @JsonProperty("qr_data")
    private String qrData;
}
