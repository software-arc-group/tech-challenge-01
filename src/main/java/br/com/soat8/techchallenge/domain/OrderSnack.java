package br.com.soat8.techchallenge.domain;

import br.com.soat8.techchallenge.adapter.out.persistence.domain.QRCodeData;
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
@Builder
public class OrderSnack {
    @JsonIgnore
    private UUID orderSnackId;
    @JsonIgnore
    private String progress;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime createdAt;

    @NotNull(message = "customer_id can not be empty")
    private UUID customerId;
    private String customerName;
    @JsonIgnore
    private String cpf;

    private List<OrderSnackItem> items;
    @JsonIgnore
    private BigDecimal totalPrice;


}
