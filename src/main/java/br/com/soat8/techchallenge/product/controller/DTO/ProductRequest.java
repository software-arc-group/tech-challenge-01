package br.com.soat8.techchallenge.product.controller.DTO;

import br.com.soat8.techchallenge.product.controller.group.OnUpdate;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductRequest {

    private UUID productId;

    private UUID categoryId;

    private String name;

    private BigDecimal price;

    private String description;
}
