package br.com.soat8.techchallenge.product.adapters.repository;

import br.com.soat8.techchallenge.product.adapters.repository.entities.ProductCategoryEntity;
import br.com.soat8.techchallenge.product.adapters.repository.entities.ProductEntity;
import br.com.soat8.techchallenge.product.core.entities.Product;
import br.com.soat8.techchallenge.product.core.entities.ProductCategory;
import br.com.soat8.techchallenge.product.utils.ProductCategoryMapper;
import br.com.soat8.techchallenge.product.utils.ProductMapper;
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
    @Autowired
    private final ProductCategoryMapper productCategoryMapper;


    @Override
    public ProductCategory findProductCategory(UUID productCategoryId) {
        return productCategoryRepository.findById(productCategoryId).map(productCategoryMapper::toProductCategory).orElse(null);
    }
}
