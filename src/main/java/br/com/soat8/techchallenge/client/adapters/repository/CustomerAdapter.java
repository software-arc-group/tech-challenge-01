package br.com.soat8.techchallenge.client.adapters.repository;

import br.com.soat8.techchallenge.client.adapters.repository.entity.CustomerEntity;
import br.com.soat8.techchallenge.client.utils.CustomerMapper;
import br.com.soat8.techchallenge.client.core.entities.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerAdapter implements CustomerGateway {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;


    public CustomerAdapter(CustomerRepository customerRepository, CustomerMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return save(customer);
    }

    @Override
    public Customer searchCustomerCpf(String cpf) {

        Customer objCustomer = new Customer();
        Optional<CustomerEntity> customerEntity = customerRepository.findByCpf(cpf);
        if(customerEntity.isPresent()){
            objCustomer = mapper.toCustomer(customerEntity.get());
        }
        return objCustomer;
    }

    @Override
    public Boolean findByCpf(String cpf) {
        return customerRepository.findByCpf(cpf).isPresent();
    }

    @Override
    public Boolean findByEmailAddress(String emailAddress) {
        return customerRepository.findByEmailAddress(emailAddress).isPresent();
    }

    private Customer save(Customer customer) {
        CustomerEntity customerEntity = mapper.toEntity(customer);
        return mapper.toCustomer(customerRepository.save(customerEntity));
    }

}
