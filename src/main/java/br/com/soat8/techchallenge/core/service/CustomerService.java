package br.com.soat8.techchallenge.core.service;

import br.com.soat8.techchallenge.application.controller.request.CustomerRequest;
import br.com.soat8.techchallenge.core.port.in.CustomerUseCase;
import br.com.soat8.techchallenge.application.gateway.CustomerGateway;
import br.com.soat8.techchallenge.core.service.mapper.CustomerMapper;
import br.com.soat8.techchallenge.entities.Customer;
import br.com.soat8.techchallenge.entities.exception.CpfAlreadyExistsException;
import br.com.soat8.techchallenge.entities.exception.CpfNotExistsException;
import br.com.soat8.techchallenge.entities.exception.EmailAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService implements CustomerUseCase {

    private final CustomerGateway customerGateway;
    private final CustomerMapper mapper;


    @Override
    public Customer saveCustomer(CustomerRequest customerRequest) {
        existCpf(customerRequest.getCpf());
        existEmail(customerRequest.getEmailAddress());
        Customer customer= mapper.toCustomer(customerRequest);
        return customerGateway.saveCustomer(customer);
    }

    @Override
    public Customer searchCustomerCpf(String cpf) {
        notExistCpf(cpf);
        return customerGateway.searchCustomerCpf(cpf);
    }

    private void notExistCpf(String cpf) {
        if (!customerGateway.findByCpf(cpf)){
            throw new CpfNotExistsException("CPF does not exist: " + cpf);
        }
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
