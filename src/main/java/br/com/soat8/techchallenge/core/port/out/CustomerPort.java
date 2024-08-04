package br.com.soat8.techchallenge.core.port.out;

import br.com.soat8.techchallenge.domain.Customer;

public interface CustomerPort {
    void saveCustomer(Customer customer);

    Boolean findByCpf(String cpf);

    Boolean findByEmailAddress(String emailAddress);
}
