package br.com.soat8.techchallenge.client.adapters.repository;

import br.com.soat8.techchallenge.client.adapters.repository.entity.CustomerEntity;
import br.com.soat8.techchallenge.client.utils.CustomerMapper;
import br.com.soat8.techchallenge.client.core.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
public class CustomerAdapter implements CustomerGateway {
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final CustomerMapper mapper;

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
    @Override
    public Boolean findByCustomerId(UUID customerId){
        return customerRepository.existsById(customerId);
    }
    @Override
    public Customer getCustomerById(UUID customerId) {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(customerId);
        return mapper.toCustomer(customerEntity.get());
    }
}
