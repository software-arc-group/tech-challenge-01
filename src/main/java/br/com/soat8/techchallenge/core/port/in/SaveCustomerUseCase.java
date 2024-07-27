package br.com.soat8.techchallenge.core.port.in;

import br.com.soat8.techchallenge.domain.Customer;

public interface SaveCustomerUseCase {
    void saveCustomer(Customer customer);
}
