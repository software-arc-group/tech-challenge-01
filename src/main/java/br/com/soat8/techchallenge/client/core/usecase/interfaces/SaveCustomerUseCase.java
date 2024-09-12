package br.com.soat8.techchallenge.client.core.usecase.interfaces;

import br.com.soat8.techchallenge.client.controller.DTO.CustomerRequest;
import br.com.soat8.techchallenge.client.core.entities.Customer;

public interface SaveCustomerUseCase {
    Customer saveCustomer(CustomerRequest customer);
}
