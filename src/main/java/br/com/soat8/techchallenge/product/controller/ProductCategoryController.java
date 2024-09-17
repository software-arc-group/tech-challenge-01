package br.com.soat8.techchallenge.product.controller;

import br.com.soat8.techchallenge.product.core.usecase.ProductCategoryUseCase;
import br.com.soat8.techchallenge.product.core.entities.ProductCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(ProductCategoryController.BASE_URL)
public class ProductCategoryController {

    public static final String BASE_URL = "/lanchonete/category";

    private final ProductCategoryUseCase productCategoryUseCase;

    public ProductCategoryController(ProductCategoryUseCase productCategoryUseCase) {
        this.productCategoryUseCase = productCategoryUseCase;
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ProductCategory> searchCategory(@PathVariable("categoryId") UUID categoryId) {
        ProductCategory productCategory = productCategoryUseCase.findProductCategory(categoryId);
        return ResponseEntity.ok(productCategory);
    }


}