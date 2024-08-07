package br.com.soat8.techchallenge.adapter.in.controller;

import br.com.soat8.techchallenge.core.port.in.ProductCategoryUseCase;
import br.com.soat8.techchallenge.domain.ProductCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(ProductCategoryController.BASE_URL)
public class ProductCategoryController {

    public static final String BASE_URL = "/lanchonete/categoria";

    private final ProductCategoryUseCase productCategoryUseCase;

    public ProductCategoryController(ProductCategoryUseCase productCategoryUseCase) {
        this.productCategoryUseCase = productCategoryUseCase;
    }

    @GetMapping("/{categoriaId}")
    public ResponseEntity<ProductCategory> cadastrarCliente(@PathVariable("categoriaId") UUID categoriaId) {
        ProductCategory productCategory = productCategoryUseCase.findProductCategory(categoriaId);
        return ResponseEntity.ok(productCategory);
    }

}
