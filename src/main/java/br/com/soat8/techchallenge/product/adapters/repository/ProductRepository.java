package br.com.soat8.techchallenge.product.adapters.repository;

import br.com.soat8.techchallenge.product.adapters.repository.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    void deleteById(UUID id);

    ProductEntity getByProductId(UUID id);
}