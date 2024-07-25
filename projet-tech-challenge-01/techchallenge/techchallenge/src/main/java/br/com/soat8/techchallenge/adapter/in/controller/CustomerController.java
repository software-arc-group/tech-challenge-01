package br.com.soat8.techchallenge.adapter.in.controller;

import br.com.soat8.techchallenge.core.port.in.CadastrarClienteUseCase;
import br.com.soat8.techchallenge.domain.Customer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lanchonete")
public class CustomerController {

    private final CadastrarClienteUseCase cadastrarClienteUseCase;

    public CustomerController(CadastrarClienteUseCase cadastrarClienteUseCase) {
        this.cadastrarClienteUseCase = cadastrarClienteUseCase;
    }

    @PostMapping("/cliente")
    public void cadastrarCliente(@RequestBody Customer customer) {
        cadastrarClienteUseCase.cadastrarCliente(customer);
    }
}
