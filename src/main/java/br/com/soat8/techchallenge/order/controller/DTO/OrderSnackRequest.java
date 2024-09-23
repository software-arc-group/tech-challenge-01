package br.com.soat8.techchallenge.order.controller.DTO;

import br.com.soat8.techchallenge.order.core.entities.OrderSnackItem;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
@Builder
public class OrderSnackRequest {
    private UUID orderSnackId;
    private String progress;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime createdAt;
    private UUID customerId;
    private String customerName;
    private String cpf;
    private List<OrderSnackItem> items;
    @JsonIgnore
    private BigDecimal totalPrice;
}
