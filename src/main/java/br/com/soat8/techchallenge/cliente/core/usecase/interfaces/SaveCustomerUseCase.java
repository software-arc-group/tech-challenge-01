package br.com.soat8.techchallenge.cliente.core.usecase.interfaces;

import br.com.soat8.techchallenge.cliente.controller.requests.CustomerRequest;
import br.com.soat8.techchallenge.entities.Customer;

public interface SaveCustomerUseCase {
    Customer saveCustomer(CustomerRequest customer);
}
