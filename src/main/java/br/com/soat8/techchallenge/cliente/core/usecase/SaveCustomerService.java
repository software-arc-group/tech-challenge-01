package br.com.soat8.techchallenge.cliente.core.usecase;

import br.com.soat8.techchallenge.cliente.controller.requests.CustomerRequest;
import br.com.soat8.techchallenge.cliente.core.usecase.interfaces.SaveCustomerUseCase;
import br.com.soat8.techchallenge.cliente.gateway.CustomerGateway;
import br.com.soat8.techchallenge.core.service.mapper.CustomerMapper;
import br.com.soat8.techchallenge.entities.Customer;
import br.com.soat8.techchallenge.entities.exception.CpfAlreadyExistsException;
import br.com.soat8.techchallenge.entities.exception.EmailAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SaveCustomerService implements SaveCustomerUseCase {

    private final CustomerGateway customerGateway;
    private final CustomerMapper mapper;


    @Override
    public Customer saveCustomer(CustomerRequest customerRequest) {
        existCpf(customerRequest.getCpf());
        existEmail(customerRequest.getEmailAddress());
        Customer customer= mapper.toCustomer(customerRequest);
        return customerGateway.saveCustomer(customer);
    }

    private void existCpf(String cpf ) {
        if (customerGateway.findByCpf(cpf)){
            throw new CpfAlreadyExistsException("CPF already exists: " + cpf );
        }
    }

    private void existEmail(String emailAddress) {
        if (customerGateway.findByEmailAddress(emailAddress)){
            throw new EmailAlreadyExistsException("Email already exists: " + emailAddress);
        }
    }
}
