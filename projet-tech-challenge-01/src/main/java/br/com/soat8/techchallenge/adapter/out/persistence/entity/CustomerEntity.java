package br.com.soat8.techchallenge.adapter.out.persistence.entity;



import jakarta.persistence.*;
import lombok.Data;

@Entity(name="customer")
@Table(name="customer")
@Data
public class CustomerEntity {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "customerId")
    private Long customerId;

    private String cpf;
    private String name;
    private String emailAddress;
}