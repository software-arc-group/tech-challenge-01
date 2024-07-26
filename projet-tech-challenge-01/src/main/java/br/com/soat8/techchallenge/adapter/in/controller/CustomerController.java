package br.com.soat8.techchallenge.adapter.in.controller;

import br.com.soat8.techchallenge.adapter.out.persistence.entity.CustomerEntity;
import br.com.soat8.techchallenge.adapter.out.persistence.retository.CustomerRepository;
import br.com.soat8.techchallenge.core.port.in.CreateCustomerUseCase;
import br.com.soat8.techchallenge.domain.Customer;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cafeteria")
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;

    public CustomerController(CreateCustomerUseCase createCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
    }

    @PostMapping("/customer")
    public void createCustomer(@RequestBody Customer customer) {
        createCustomerUseCase.createCustomer(customer);
    }

    /*teste de implementaçao swagger*/
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    @Operation(summary = " list customer ", description = " list all customers ")
    public List<CustomerEntity> getAll(){
        return customerRepository.findAll();
    }
    /*teste de implementaçao swagger*/

}
