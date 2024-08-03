package br.com.soat8.techchallenge.adapter.in.controller;

import br.com.soat8.techchallenge.core.port.in.SaveCustomerUseCase;
import br.com.soat8.techchallenge.core.service.QRCodeService;
import br.com.soat8.techchallenge.domain.Customer;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lanchonete")
public class CustomerController {

    private final SaveCustomerUseCase saveCustomerUseCase;
    private final QRCodeService qrCodeService;

    public CustomerController(SaveCustomerUseCase saveCustomerUseCase, QRCodeService qrCodeService) {
        this.saveCustomerUseCase = saveCustomerUseCase;
        this.qrCodeService = qrCodeService;
    }

    @PostMapping("/customer")
    public ResponseEntity<Void> cadastrarCliente(@Valid @RequestBody Customer customer) {
        saveCustomerUseCase.saveCustomer(customer);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
