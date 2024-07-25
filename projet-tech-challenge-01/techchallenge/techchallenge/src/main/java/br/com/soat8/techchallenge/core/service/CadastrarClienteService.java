package br.com.soat8.techchallenge.core.service;

import br.com.soat8.techchallenge.core.port.in.CadastrarClienteUseCase;
import br.com.soat8.techchallenge.core.port.out.SaveCustomerPort;
import br.com.soat8.techchallenge.domain.Customer;
import org.springframework.stereotype.Service;

@Service
public class CadastrarClienteService implements CadastrarClienteUseCase {

    private final SaveCustomerPort saveCustomerPort;

    public CadastrarClienteService(SaveCustomerPort saveCustomerPort) {
        this.saveCustomerPort = saveCustomerPort;
    }

    @Override
    public void cadastrarCliente(Customer customer) {
        saveCustomerPort.saveCustomer(customer);
    }
}
