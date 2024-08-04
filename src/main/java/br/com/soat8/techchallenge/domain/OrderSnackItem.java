package br.com.soat8.techchallenge.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderSnackItem {
     private Product product;
     private Integer amount;

    BigDecimal getTotalPrice(){
        BigDecimal amountAsBigDecimal = BigDecimal.valueOf(amount);
        return product.getPrice().multiply(amountAsBigDecimal);
    }
}
