package br.com.soat8.techchallenge.client.adapters.repository;

import br.com.soat8.techchallenge.client.core.entities.Customer;

import java.util.UUID;

public interface CustomerGateway {
    Customer saveCustomer(Customer customer);

    Customer searchCustomerCpf(String cpf);

    Boolean findByCpf(String cpf);

    Boolean findByEmailAddress(String emailAddress);

    Boolean findByCustomerId(UUID customerId);

    Customer getCustomerById(UUID customerId);
}
