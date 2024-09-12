package br.com.soat8.techchallenge.client.adapters.repository;

import br.com.soat8.techchallenge.client.core.entities.Customer;

public interface CustomerGateway {
    Customer saveCustomer(Customer customer);

    Customer searchCustomerCpf(String cpf);

    Boolean findByCpf(String cpf);

    Boolean findByEmailAddress(String emailAddress);
}
