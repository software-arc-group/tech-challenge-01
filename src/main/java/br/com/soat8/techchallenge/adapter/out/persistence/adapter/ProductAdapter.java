package br.com.soat8.techchallenge.adapter.out.persistence.adapter;

import br.com.soat8.techchallenge.adapter.out.persistence.entity.CustomerEntity;
import br.com.soat8.techchallenge.adapter.out.persistence.entity.ProductCategoryEntity;
import br.com.soat8.techchallenge.adapter.out.persistence.entity.ProductEntity;
import br.com.soat8.techchallenge.adapter.out.persistence.retository.CustomerRepository;
import br.com.soat8.techchallenge.adapter.out.persistence.retository.ProductCategoryRepository;
import br.com.soat8.techchallenge.adapter.out.persistence.retository.ProductRepository;
import br.com.soat8.techchallenge.core.port.out.CustomerPort;
import br.com.soat8.techchallenge.core.port.out.ProductPort;
import br.com.soat8.techchallenge.domain.Customer;
import br.com.soat8.techchallenge.domain.Product;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductAdapter implements ProductPort {

    private final ProductRepository productRepository;

    //private final ProductCategoryRepository productCategoryRepository;

    public ProductAdapter(ProductRepository productRepository/*, ProductCategoryRepository productCategoryRepository*/) {
        this.productRepository = productRepository;
        //this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public void saveProduct(Product product) {
        saveOrUpdate(product);
    }

    @Override
    public void updateProduct(Product product) {
        saveOrUpdate(product);
    }

    @Override
    public void removeProduct(Integer product_id) {
        remove(product_id);
    }

    @Override
    public Boolean findById(Integer id) {
        return productRepository.findById(id).isPresent();
    }

    private void saveOrUpdate(Product product) {
        ProductEntity productEntity = new ProductEntity();
        ProductCategoryEntity productCategoryEntity = new ProductCategoryEntity();

        productCategoryEntity.setProductCategoryId(product.getCategory().getProductCategoryId());
        productCategoryEntity.setDescription(product.getCategory().getDescription());

        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());
        productEntity.setCategory(productCategoryEntity);
        productEntity.setDescription(product.getDescription());

        if(product.getProductId()!=null){
            productEntity.setProductId(product.getProductId());
        }

        productRepository.save(productEntity);
    }

    private void remove(Integer product) {
        productRepository.deleteById(product);
    }

}
