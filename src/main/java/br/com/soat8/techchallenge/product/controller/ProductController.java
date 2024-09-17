package br.com.soat8.techchallenge.product.controller;

import br.com.soat8.techchallenge.product.core.usecase.ProductUseCase;
import br.com.soat8.techchallenge.product.core.entities.Product;
import br.com.soat8.techchallenge.product.controller.group.OnCreate;
import br.com.soat8.techchallenge.product.controller.group.OnUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(ProductController.BASE_URL)
public class ProductController {

    public static final String BASE_URL = "/lanchonete/product";

    private final ProductUseCase productUseCase;

    public ProductController(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@Validated(OnCreate.class) @RequestBody Product product) {
        productUseCase.saveProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@Validated(OnUpdate.class) @RequestBody Product product) {
        productUseCase.saveProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> removeProduct(@PathVariable("productId") UUID productId) {
        productUseCase.removeProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}