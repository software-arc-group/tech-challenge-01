package br.com.soat8.techchallenge.client.controller;

import br.com.soat8.techchallenge.client.controller.DTO.CustomerRequest;
import br.com.soat8.techchallenge.client.core.usecase.interfaces.SaveCustomerUseCase;
import br.com.soat8.techchallenge.client.core.usecase.interfaces.SearchCpfCustomerUseCase;
import br.com.soat8.techchallenge.client.core.entities.Customer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController implements CustomerApi{

    public static final String BASE_URL = "/lanchonete/customer";

    @Autowired
    private final SaveCustomerUseCase customerUseCase;
    @Autowired
    private final SearchCpfCustomerUseCase searchCustomerCpfService;
    @Autowired
    private final CustomerPresenterRest customerPresenterRest;


    @PostMapping
    public ResponseEntity<Void> createCustomer(@Valid @RequestBody CustomerRequest customer) {
        Customer createdCustomer = customerUseCase.saveCustomer(customer);
        return customerPresenterRest.generateCustomerCreatedResponse(createdCustomer);
    }

    @GetMapping
    public ResponseEntity<Customer> searchCustomerCpf( String cpf) {

        Customer result = searchCustomerCpfService.searchCustomerCpf(cpf);

        return ResponseEntity.ok(result);
    }

}