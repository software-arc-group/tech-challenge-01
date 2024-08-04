package br.com.soat8.techchallenge.adapter.out.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class MercadoPagoItem {
    public String skuNumber;
    public String title;
    public BigDecimal unitPrice;
    public int quantity;
    public String unitMeasure;
    public BigDecimal totalAmount;

    public MercadoPagoItem(String skuNumber, String title, BigDecimal unitPrice, int quantity, String unitMeasure) {
        this.skuNumber = skuNumber;
        this.title = title;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.unitMeasure = unitMeasure;
        this.totalAmount = unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
