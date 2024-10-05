package br.com.soat8.techchallenge.client.controller;

import br.com.soat8.techchallenge.client.controller.DTO.CustomerRequest;
import br.com.soat8.techchallenge.client.core.entities.Customer;
import org.springframework.http.ResponseEntity;

public interface CustomerApi {
    ResponseEntity<Void> createCustomer( CustomerRequest customer);
    ResponseEntity<Customer> searchCustomerCpf(String cpf);
}
