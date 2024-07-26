package br.com.soat8.techchallenge.core.service;

import br.com.soat8.techchallenge.core.port.in.CreateCustomerUseCase;
import br.com.soat8.techchallenge.core.port.out.SaveCustomerPort;
import br.com.soat8.techchallenge.domain.Customer;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerService implements CreateCustomerUseCase {

    private final SaveCustomerPort saveCustomerPort;

    public CreateCustomerService(SaveCustomerPort saveCustomerPort) {
        this.saveCustomerPort = saveCustomerPort;
    }

    @Override
    public void createCustomer(Customer customer) {
        saveCustomerPort.saveCustomer(customer);
    }
}
