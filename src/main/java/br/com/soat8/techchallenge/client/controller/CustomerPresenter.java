package br.com.soat8.techchallenge.client.controller;

import br.com.soat8.techchallenge.client.core.entities.Customer;
import org.springframework.http.ResponseEntity;

public interface CustomerPresenter {
    ResponseEntity<Void> generateCustomerCreatedResponse(Customer customer);
}
