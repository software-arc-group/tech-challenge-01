package br.com.soat8.techchallenge.item.adapter.repository;

import br.com.soat8.techchallenge.item.adapter.repository.entities.OrderSnackItemEntity;
import br.com.soat8.techchallenge.order.adapters.repository.entities.OrderSnackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface OrderSnackItemRepository extends JpaRepository<OrderSnackItemEntity, UUID> {
}
