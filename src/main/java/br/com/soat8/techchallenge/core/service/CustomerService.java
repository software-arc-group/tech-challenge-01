package br.com.soat8.techchallenge.core.service;

import br.com.soat8.techchallenge.application.controller.request.CustomerRequest;
import br.com.soat8.techchallenge.core.port.in.CustomerUseCase;
import br.com.soat8.techchallenge.application.gateway.CustomerGateway;
import br.com.soat8.techchallenge.core.service.mapper.CustomerMapper;
import br.com.soat8.techchallenge.entities.Customer;
import br.com.soat8.techchallenge.entities.exception.CpfAlreadyExistsException;
import br.com.soat8.techchallenge.entities.exception.CpfNotExistsException;
import br.com.soat8.techchallenge.entities.exception.EmailAlreadyExistsException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements CustomerUseCase {

    private final CustomerGateway customerGateway;
    private final CustomerMapper mapper;

    public CustomerService(CustomerGateway customerGateway, CustomerMapper mapper ) {
        this.customerGateway = customerGateway;
        this.mapper = mapper;
    }

    @Override
    public Customer saveCustomer(CustomerRequest customer) {
        existCpf(customer);
        existEmail(customer);
        Customer customerEntity= mapper.toCustomer(customer);
        return customerGateway.saveCustomer(customerEntity);
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

    private void existCpf(CustomerRequest customer) {
        if (customerGateway.findByCpf(customer.getCpf())){
            throw new CpfAlreadyExistsException("CPF already exists: " + customer.getCpf());
        }
    }

    private void existEmail(CustomerRequest customer) {
        if (customerGateway.findByEmailAddress(customer.getEmailAddress())){
            throw new EmailAlreadyExistsException("Email already exists: " + customer.getEmailAddress());
        }
    }
}
