package br.com.soat8.techchallenge.core.port.out;

import br.com.soat8.techchallenge.domain.Customer;

public interface SaveCustomerPort {
    void saveCustomer(Customer customer);
}
