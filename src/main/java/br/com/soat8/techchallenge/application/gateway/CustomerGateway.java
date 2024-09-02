package br.com.soat8.techchallenge.application.gateway;

import br.com.soat8.techchallenge.entities.Customer;

public interface CustomerGateway {
    void saveCustomer(Customer customer);

    Customer searchCustomerCpf(String cpf);

    Boolean findByCpf(String cpf);

    Boolean findByEmailAddress(String emailAddress);
}
