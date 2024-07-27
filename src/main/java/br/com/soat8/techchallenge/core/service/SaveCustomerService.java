package br.com.soat8.techchallenge.core.service;

import br.com.soat8.techchallenge.core.port.in.SaveCustomerUseCase;
import br.com.soat8.techchallenge.core.port.out.SaveCustomerPort;
import br.com.soat8.techchallenge.domain.Customer;
import org.springframework.stereotype.Service;

@Service
public class SaveCustomerService implements SaveCustomerUseCase {

    private final SaveCustomerPort saveCustomerPort;

    public SaveCustomerService(SaveCustomerPort saveCustomerPort) {
        this.saveCustomerPort = saveCustomerPort;
    }

    @Override
    public void saveCustomer(Customer customer) {
        saveCustomerPort.saveCustomer(customer);
    }
}
