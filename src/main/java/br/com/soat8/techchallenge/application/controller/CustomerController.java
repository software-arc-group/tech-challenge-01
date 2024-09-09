package br.com.soat8.techchallenge.application.controller;

import br.com.soat8.techchallenge.application.controller.request.CustomerRequest;
import br.com.soat8.techchallenge.application.presenter.CustomerPresenterRest;
import br.com.soat8.techchallenge.core.port.in.CustomerUseCase;
import br.com.soat8.techchallenge.entities.Customer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/lanchonete/customer";

    private final CustomerUseCase customerUseCase;
    private final CustomerPresenterRest customerPresenterRest;


    @PostMapping
    public ResponseEntity<Void> createCustomer(@Valid @RequestBody CustomerRequest customer) {
        Customer createdCustomer = customerUseCase.saveCustomer(customer);
        return customerPresenterRest.generateCustomerCreatedResponse(createdCustomer);
    }

    @GetMapping
    public ResponseEntity<Customer> searchCustomerCpf(@Valid @NotBlank @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}", message = "CPF invalid") String cpf) {

        Customer result = customerUseCase.searchCustomerCpf(cpf);

        return ResponseEntity.ok(result);
    }

}