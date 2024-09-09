package br.com.soat8.techchallenge.application.presenter;

import br.com.soat8.techchallenge.entities.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class CustomerPresenterRest {
    public ResponseEntity<Void> generateCustomerCreatedResponse(Customer customer){
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId()).toUri()).build();
    }
}
