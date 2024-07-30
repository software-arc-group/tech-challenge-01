package br.com.soat8.techchallenge.core.service;

import br.com.soat8.techchallenge.core.port.in.SaveCustomerUseCase;
import br.com.soat8.techchallenge.core.port.out.SaveCustomerPort;
import br.com.soat8.techchallenge.domain.Customer;
import br.com.soat8.techchallenge.domain.exception.CpfAlreadyExistsException;
import br.com.soat8.techchallenge.domain.exception.EmailAlreadyExistsException;
import org.springframework.stereotype.Service;

@Service
public class SaveCustomerService implements SaveCustomerUseCase {

    private final SaveCustomerPort saveCustomerPort;

    public SaveCustomerService(SaveCustomerPort saveCustomerPort) {
        this.saveCustomerPort = saveCustomerPort;
    }

    @Override
    public void saveCustomer(Customer customer) {
        existCpf(customer);
        existEmail(customer);
        saveCustomerPort.saveCustomer(customer);
    }

    private void existCpf(Customer customer) {
        if (saveCustomerPort.findByCpf(customer.getCpf())){
            throw new CpfAlreadyExistsException("CPF already exists: " + customer.getCpf());
        }
    }

    private void existEmail(Customer customer) {
        if (saveCustomerPort.findByEmailAddress(customer.getEmailAddress())){
            throw new EmailAlreadyExistsException("Email already exists: " + customer.getEmailAddress());
        }
    }
}
