package br.com.soat8.techchallenge.adapter.out.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "customer")
public class CustomerEntity {
    @Id
    private UUID customerId;
    private String name;
    private String emailAddress;
    private String cpf;
}
