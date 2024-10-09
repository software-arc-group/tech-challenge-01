package br.com.soat8.techchallenge.order.core.entities;

import br.com.soat8.techchallenge.client.core.entities.Customer;
import br.com.soat8.techchallenge.order.controller.DTO.OrderProgressRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSnack {
    private UUID orderSnackId;
    private String progress = OrderProgressRequest.RECEIVED.name();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime createdAt = LocalDateTime.now();

    Customer customer;

    private List<OrderSnackItem> items;
    @JsonIgnore
    private BigDecimal totalPrice;

    public void setItems(List<OrderSnackItem> items) {
        this.items = items;
        this.totalPrice = calculateItems(items);
    }

    private BigDecimal calculateItems(List<OrderSnackItem> items) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        if (items != null){
            for (OrderSnackItem item : items) {
                BigDecimal itemTotal = item.getAmount();
                totalPrice = totalPrice.add(itemTotal);
            }
        }
        return totalPrice;
    }
}
