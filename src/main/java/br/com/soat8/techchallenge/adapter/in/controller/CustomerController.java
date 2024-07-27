package br.com.soat8.techchallenge.adapter.in.controller;

import br.com.soat8.techchallenge.core.port.in.SaveCustomerUseCase;
import br.com.soat8.techchallenge.domain.Customer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lanchonete")
public class CustomerController {

    private final SaveCustomerUseCase saveCustomerUseCase;

    public CustomerController(SaveCustomerUseCase saveCustomerUseCase) {
        this.saveCustomerUseCase = saveCustomerUseCase;
    }

    @PostMapping("/cliente")
    public void cadastrarCliente(@RequestBody Customer customer) {
        saveCustomerUseCase.saveCustomer(customer);
    }
}
