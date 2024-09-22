package br.com.soat8.techchallenge.product.controller;

import br.com.soat8.techchallenge.product.core.usecase.interfaces.ProductCategoryUseCase;
import br.com.soat8.techchallenge.product.core.entities.ProductCategory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(ProductCategoryController.BASE_URL)
public class ProductCategoryController {

    public static final String BASE_URL = "/lanchonete/category";

    @Autowired
    private final ProductCategoryUseCase productCategoryUseCase;

    @GetMapping("/{categoryId}")
    public ResponseEntity<ProductCategory> searchCategory(@PathVariable("categoryId") UUID categoryId) {
        ProductCategory productCategory = productCategoryUseCase.findProductCategory(categoryId);
        return ResponseEntity.ok(productCategory);
    }


}