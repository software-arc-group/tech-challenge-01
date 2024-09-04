package br.com.soat8.techchallenge.port.in;

import br.com.soat8.techchallenge.domain_old.Customer;

public interface CustomerUseCase {
    void saveCustomer(Customer customer);
    Customer searchCustomerCpf(String cpf);
}
