package br.com.soat8.techchallenge.cliente.core.usecase.interfaces;

import br.com.soat8.techchallenge.entities.Customer;

public interface SearchCpfCustomerUseCase {
    Customer searchCustomerCpf(String cpf);
}
