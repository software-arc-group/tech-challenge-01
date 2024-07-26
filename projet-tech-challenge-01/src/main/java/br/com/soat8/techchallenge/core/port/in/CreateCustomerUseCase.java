package br.com.soat8.techchallenge.core.port.in;

import br.com.soat8.techchallenge.domain.Customer;

public interface CreateCustomerUseCase {
    void createCustomer(Customer customer);
}
