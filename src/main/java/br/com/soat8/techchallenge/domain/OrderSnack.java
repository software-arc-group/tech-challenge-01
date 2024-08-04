package br.com.soat8.techchallenge.domain;

import jakarta.persistence.criteria.Order;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderSnack {
    private List<OrderSnackItem> items;
    private Customer customer;
    private BigDecimal totalAmount;


    public BigDecimal getTotalAmount(){
        this.items
                .forEach((item -> this.totalAmount.add(item.getTotalPrice())));
        return totalAmount;
    }

}
