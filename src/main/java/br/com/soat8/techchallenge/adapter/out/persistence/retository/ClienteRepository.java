package br.com.soat8.techchallenge.adapter.out.persistence.retository;

import br.com.soat8.techchallenge.adapter.out.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<CustomerEntity, Long> {
}
