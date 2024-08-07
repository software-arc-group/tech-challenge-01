package br.com.soat8.techchallenge.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSnackItem {
     @JsonProperty("product_id")
     private UUID productId;
     private BigDecimal price;
     private Integer quantity;
}
