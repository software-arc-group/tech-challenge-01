package br.com.soat8.techchallenge.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.criteria.Order;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSnack {
    private List<OrderSnackItem> items;
    @JsonIgnore
    private BigDecimal totalPrice;
    @NotNull(message = "customer_id can not be empty")
    private UUID customerId;
}
