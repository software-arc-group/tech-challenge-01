package br.com.soat8.techchallenge.product.core.usecase;

import br.com.soat8.techchallenge.product.adapters.repository.ProductPort;
import br.com.soat8.techchallenge.product.controller.DTO.ProductRequest;
import br.com.soat8.techchallenge.product.core.entities.Product;
import br.com.soat8.techchallenge.product.core.exceptions.NotFoundProductException;
import br.com.soat8.techchallenge.product.core.usecase.interfaces.GetProductUseCase;
import br.com.soat8.techchallenge.product.core.usecase.interfaces.UpdateProductUseCase;
import br.com.soat8.techchallenge.product.utils.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductService implements UpdateProductUseCase {

    @Autowired
    GetProductUseCase getProductUseCase;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductPort productPort;


    @Override
    public Product update(ProductRequest productRequest) {
        Product product = getProductUseCase.getProduct(productRequest.getProductId().toString());
        if(product == null){
            throw new NotFoundProductException("Item with Id: "+ productRequest.getProductId()+" not found");
        }
        Product updatedProduct = updateProduct(product, productRequest);
        return productPort.saveOrUpdate(updatedProduct);
    }
    private Product updateProduct(Product product, ProductRequest productRequest){
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        return product;
    }
}
