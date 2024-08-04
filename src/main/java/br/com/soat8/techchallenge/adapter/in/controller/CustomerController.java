package br.com.soat8.techchallenge.adapter.in.controller;

import br.com.soat8.techchallenge.core.port.in.CustomerUseCase;
import br.com.soat8.techchallenge.domain.Customer;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lanchonete")
public class CustomerController {

    private final CustomerUseCase customerUseCase;

    public CustomerController(CustomerUseCase customerUseCase) {
        this.customerUseCase = customerUseCase;
    }

    @PostMapping("/customer")
    public ResponseEntity<Void> cadastrarCliente(@Valid @RequestBody Customer customer) {
        customerUseCase.saveCustomer(customer);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
