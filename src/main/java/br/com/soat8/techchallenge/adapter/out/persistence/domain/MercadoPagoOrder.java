package br.com.soat8.techchallenge.adapter.out.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MercadoPagoOrder {
    private MercadoPagoCashOut cashOut;
    private String description;
    private String externalReference;
    private List<MercadoPagoItem> items;
    private String title;
    private BigDecimal totalAmount;
}
