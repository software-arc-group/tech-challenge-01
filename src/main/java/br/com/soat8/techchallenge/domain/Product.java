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
public class Product {

    private UUID productId;
    private String name;
    private BigDecimal price;
    private ProductCategory category;
    private String description;


    //aplicar anotações referente a campos vazios com javax @valid no controller

}