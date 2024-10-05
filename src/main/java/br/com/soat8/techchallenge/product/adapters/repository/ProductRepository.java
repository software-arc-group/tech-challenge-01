package br.com.soat8.techchallenge.product.adapters.repository;

import br.com.soat8.techchallenge.product.adapters.repository.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    //Não consegui fazer o delete funcionar com o deleteById, não entendi o motivo
    @Modifying
    @Query("DELETE FROM ProductEntity t WHERE t.productId = :pid")
    void deleteByProductId(@Param("pid") UUID id);
}