package br.com.soat8.techchallenge.product.core.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategory {

    @JsonProperty("productCategoryId")
    private UUID productCategoryId;

    private String description;

    private List<Product> products;

    public void addProducts(Product product){
        if(products == null){
            products =new ArrayList<Product>();
        }
        if(!products.contains(product))
            products.add(product);
    }
}