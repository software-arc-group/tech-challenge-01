package br.com.soat8.techchallenge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategory {

    private UUID productCategoryId;

    private String description;

    private List<Product> products;
}
