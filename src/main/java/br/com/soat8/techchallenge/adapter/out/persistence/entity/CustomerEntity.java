package br.com.soat8.techchallenge.adapter.out.persistence.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CustomerEntity {
    @Id
    private Long customerId;
    private String name;
    private String emailAddress;
    private String cpf;
}
