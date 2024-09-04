package br.com.soat8.techchallenge.core_old.port.out;

import br.com.soat8.techchallenge.domain_old.Customer;

public interface CustomerPort {
    void saveCustomer(Customer customer);

    Customer searchCustomerCpf(String cpf);

    Boolean findByCpf(String cpf);

    Boolean findByEmailAddress(String emailAddress);
}
