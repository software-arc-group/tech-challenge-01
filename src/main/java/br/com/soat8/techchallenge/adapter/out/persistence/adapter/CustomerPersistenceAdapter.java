package br.com.soat8.techchallenge.adapter.out.persistence.adapter;

import br.com.soat8.techchallenge.adapter.out.persistence.retository.CustomerRepository;
import br.com.soat8.techchallenge.adapter.out.persistence.entity.CustomerEntity;
import br.com.soat8.techchallenge.core.port.out.SaveCustomerPort;
import br.com.soat8.techchallenge.domain.Customer;
import br.com.soat8.techchallenge.domain.exception.CpfAlreadyExistsException;
import br.com.soat8.techchallenge.domain.exception.EmailAlreadyExistsException;
import org.springframework.stereotype.Component;

@Component
public class CustomerPersistenceAdapter implements SaveCustomerPort {

    private final CustomerRepository customerRepository;

    public CustomerPersistenceAdapter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void saveCustomer(Customer customer) {

        existCpf(customer);
        existEmail(customer);

        save(customer);
    }

    private void save(Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customer.getName());
        customerEntity.setEmailAddress(customer.getEmailAddress());
        customerEntity.setCpf(customer.getCpf());
        customerRepository.save(customerEntity);
    }

    private void existCpf(Customer customer) {
        if (customerRepository.findByCpf(customer.getCpf()).isPresent()){
            throw new CpfAlreadyExistsException("CPF already exists: " + customer.getCpf());
        }
    }

    private void existEmail(Customer customer) {
        if (customerRepository.findByEmailAddress(customer.getEmailAddress()).isPresent()){
            throw new EmailAlreadyExistsException("Email already exists: " + customer.getEmailAddress());
        }
    }
}
