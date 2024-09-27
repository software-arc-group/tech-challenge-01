package br.com.soat8.techchallenge.product.core.entities;

import br.com.soat8.techchallenge.order.adapters.repository.entities.OrderSnackItemEntity;
import br.com.soat8.techchallenge.order.core.entities.OrderSnackItem;
import br.com.soat8.techchallenge.product.controller.group.OnUpdate;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Product {

    @NotNull(groups = OnUpdate.class, message = "CategoryId is required for update")
    private UUID productId;

    private ProductCategory category;

    @NotNull(message = "name product required")
    private String name;

    private BigDecimal price;

    private String description;

    private List<OrderSnackItem> orderItems;

    public void setCategory(ProductCategory productCategory){
        productCategory.addProducts(this);
        this.category = productCategory;
    }

    public void setOrderItems(List<OrderSnackItem> listOrderSnackItem){
        if (listOrderSnackItem == null){
            orderItems = listOrderSnackItem;
            return;
        }
        for(OrderSnackItem item : listOrderSnackItem) {
            item.setProduct(this);
        }
        orderItems = listOrderSnackItem;
    }

}