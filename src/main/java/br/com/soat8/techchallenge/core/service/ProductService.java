package br.com.soat8.techchallenge.core.service;

import br.com.soat8.techchallenge.core.port.in.ProductUseCase;
import br.com.soat8.techchallenge.core.port.out.ProductPort;
import br.com.soat8.techchallenge.core.port.out.ProductCategoryPort;
import br.com.soat8.techchallenge.domain.Product;
import br.com.soat8.techchallenge.domain.ProductCategory;
import br.com.soat8.techchallenge.domain.exception.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class ProductService implements ProductUseCase {

    private final ProductPort productPort;

    //private final ProductCategoryPort productCategoryPort;

    public ProductService(ProductPort productPort/*, ProductCategoryPort productCategoryPort*/) {
        this.productPort = productPort;
        //this.productCategoryPort = productCategoryPort;
    }

    @Override
    public void saveProduct(Product product) {
        invalidCategory(product);
        //invalidCategory(product.getCategory().getProductCategoryId());
        incompleteFields(product);
        productPort.saveProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        invalidCategory(product);
        notFoundProduct(product.getProductId());
        productPort.updateProduct(product);
    }

    @Override
    public void removeProduct(UUID productId) {
        notFoundProduct(productId);
        productPort.removeProduct(productId);
    }

    private void invalidCategory(Product product) {
        /*if (productCategoryPort.findProductCategory(product.getCategory().getProductCategoryId())){
            throw new InvalidCategoryException("Invalid Category: " + productCategoryId);
        }*/
    }

    private void incompleteFields(Product product) {
        if (product.getName().isEmpty()){
            throw new IncompleteFieldsException("Incomplete fields 'Name' ");
        }
        if (product.getPrice()==null){
            throw new IncompleteFieldsException("Incomplete fields 'Price' ");
        }
        if (product.getCategory()==null){
            throw new IncompleteFieldsException("Incomplete fields 'Category' ");
        }
        if (product.getDescription().isEmpty()){
            throw new IncompleteFieldsException("Incomplete fields 'Description' ");
        }
    }

    private void notFoundProduct(UUID productId) {
        if (productPort.findById(productId)){
            throw new NotFoundProductException("This product does not exist Id: " + productId);
        }
    }

}