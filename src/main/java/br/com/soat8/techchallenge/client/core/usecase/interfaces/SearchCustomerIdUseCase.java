package br.com.soat8.techchallenge.client.core.usecase.interfaces;

import br.com.soat8.techchallenge.client.core.entities.Customer;

public interface SearchCustomerIdUseCase {
    public Customer searchById(String id);
}
