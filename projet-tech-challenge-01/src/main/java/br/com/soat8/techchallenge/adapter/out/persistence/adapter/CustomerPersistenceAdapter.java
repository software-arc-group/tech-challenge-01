package br.com.soat8.techchallenge.adapter.out.persistence.adapter;

import br.com.soat8.techchallenge.adapter.out.persistence.retository.CustomerRepository;
import br.com.soat8.techchallenge.adapter.out.persistence.entity.CustomerEntity;
import br.com.soat8.techchallenge.core.port.out.SaveCustomerPort;
import br.com.soat8.techchallenge.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerPersistenceAdapter implements SaveCustomerPort {

    private final CustomerRepository customerRepository;

    public CustomerPersistenceAdapter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void saveCustomer(Customer customer) {
        CustomerEntity customerJpaEntity = new CustomerEntity();
        customerJpaEntity.setName(customer.getName());
        customerJpaEntity.setEmailAddress(customer.getEmailAddress());
        customerJpaEntity.setCpf(customer.getCpf());
        customerRepository.save(customerJpaEntity);
    }
}
