package br.com.soat8.techchallenge.client.controller;

import br.com.soat8.techchallenge.client.core.entities.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class CustomerPresenterRest implements  CustomerPresenter {
    public ResponseEntity<Void> generateCustomerCreatedResponse(Customer customer){
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId()).toUri()).build();
    }
}
