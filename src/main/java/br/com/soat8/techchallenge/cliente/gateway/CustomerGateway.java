package br.com.soat8.techchallenge.cliente.gateway;

import br.com.soat8.techchallenge.entities.Customer;

public interface CustomerGateway {
    Customer saveCustomer(Customer customer);

    Customer searchCustomerCpf(String cpf);

    Boolean findByCpf(String cpf);

    Boolean findByEmailAddress(String emailAddress);
}
