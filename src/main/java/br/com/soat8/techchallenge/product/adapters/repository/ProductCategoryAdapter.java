package br.com.soat8.techchallenge.product.adapters.repository;

import br.com.soat8.techchallenge.product.adapters.repository.entities.ProductCategoryEntity;
import br.com.soat8.techchallenge.product.adapters.repository.entities.ProductEntity;
import br.com.soat8.techchallenge.product.core.entities.Product;
import br.com.soat8.techchallenge.product.core.entities.ProductCategory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductCategoryAdapter implements ProductCategoryPort {

    @Autowired
    private final ProductCategoryRepository productCategoryRepository;


    @Override
    public ProductCategory findProductCategory(UUID productCategoryId) {
        return productCategoryRepository.findById(productCategoryId).map(this::build).orElse(null);
    }

    private ProductCategory build(ProductCategoryEntity entity) {
        if (entity == null) {
            return null;
        }
        return ProductCategory.builder()
                .productCategoryId(entity.getProductCategoryId())
                .description(entity.getDescription())
                .products(buildProductList(entity.getProducts()))
                .build();
    }

    private List<Product> buildProductList(List<ProductEntity> products) {
        return products.stream().map(this::buildProduct).collect(Collectors.toList());
    }

    private Product buildProduct(ProductEntity entity) {
        if (entity == null) {
            return null;
        }
        return Product.builder()
                .productId(entity.getProductId())
                .categoryId(entity.getCategory().getProductCategoryId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .build();
    }

}
