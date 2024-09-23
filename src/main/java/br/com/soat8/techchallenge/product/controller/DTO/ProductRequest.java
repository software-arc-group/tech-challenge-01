package br.com.soat8.techchallenge.product.controller.DTO;

import br.com.soat8.techchallenge.product.controller.group.OnUpdate;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductRequest {

    private UUID productId;

    private UUID categoryId;

    private String name;

    private BigDecimal price;

    private String description;
}
