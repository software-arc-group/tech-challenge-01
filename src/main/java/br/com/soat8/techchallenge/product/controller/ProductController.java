package br.com.soat8.techchallenge.product.controller;

import br.com.soat8.techchallenge.product.controller.DTO.ProductRequest;
import br.com.soat8.techchallenge.product.core.entities.Product;
import br.com.soat8.techchallenge.product.controller.group.OnCreate;
import br.com.soat8.techchallenge.product.controller.group.OnUpdate;
import br.com.soat8.techchallenge.product.core.usecase.interfaces.RemoveProductUseCase;
import br.com.soat8.techchallenge.product.core.usecase.interfaces.SaveProductUseCase;
import br.com.soat8.techchallenge.product.core.usecase.interfaces.UpdateProductUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(ProductController.BASE_URL)
@AllArgsConstructor
public class ProductController {

    public static final String BASE_URL = "/lanchonete/product";

    @Autowired
    private final SaveProductUseCase saveProductUseCase;

    @Autowired
    private final UpdateProductUseCase updateProductUseCase;

    @Autowired
    private final RemoveProductUseCase removeProductUseCase;

    @PostMapping
    public ResponseEntity<Void> createProduct(@Validated(OnCreate.class) @RequestBody ProductRequest productRequest) {
        saveProductUseCase.saveProduct(productRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@Validated(OnUpdate.class) @RequestBody ProductRequest productRequest) {
        Product product= updateProductUseCase.update(productRequest);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> removeProduct(@PathVariable("productId") UUID productId) {
        removeProductUseCase.removeProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}