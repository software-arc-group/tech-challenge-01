package br.com.soat8.techchallenge.product.adapters.repository;

import br.com.soat8.techchallenge.product.adapters.repository.entities.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, UUID> {

}