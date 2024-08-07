package br.com.soat8.techchallenge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderSnackItem {
    private UUID orderSnackItemId;
    private BigDecimal amount;
    private UUID productId;
    private String productName;
}
