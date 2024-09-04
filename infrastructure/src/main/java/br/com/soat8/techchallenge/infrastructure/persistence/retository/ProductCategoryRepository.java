package br.com.soat8.techchallenge.adapter_old.out.persistence.retository;

import br.com.soat8.techchallenge.adapter_old.out.persistence.entity.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, UUID> {

}