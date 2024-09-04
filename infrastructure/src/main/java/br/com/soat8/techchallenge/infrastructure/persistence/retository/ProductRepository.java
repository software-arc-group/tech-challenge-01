package br.com.soat8.techchallenge.infrastructure.persistence.retository;

import br.com.soat8.techchallenge.adapter_old.out.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    void deleteById(UUID id);

}