package br.com.soat8.techchallenge.adapter.in.controller;

import br.com.soat8.techchallenge.core.port.in.ProductCategoryUseCase;
import br.com.soat8.techchallenge.domain.ProductCategory;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Void> createCategory(@Valid @RequestBody ProductCategory productCategory) {
        productCategoryUseCase.saveProductCategory(productCategory);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}