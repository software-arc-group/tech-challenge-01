package br.com.soat8.techchallenge.client.core.usecase.interfaces;

import br.com.soat8.techchallenge.client.core.entities.Customer;

public interface SearchCpfCustomerUseCase {
    Customer searchCustomerCpf(String cpf);
}
