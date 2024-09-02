package br.com.soat8.techchallenge.core.port.in;

import br.com.soat8.techchallenge.application.controller.request.CustomerRequest;
import br.com.soat8.techchallenge.entities.Customer;

public interface CustomerUseCase {
    Customer saveCustomer(CustomerRequest customer);
    Customer searchCustomerCpf(String cpf);
}
