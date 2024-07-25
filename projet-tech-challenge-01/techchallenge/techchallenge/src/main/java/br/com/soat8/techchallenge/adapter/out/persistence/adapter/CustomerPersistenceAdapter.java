package br.com.soat8.techchallenge.adapter.out.persistence.adapter;

import br.com.soat8.techchallenge.adapter.out.persistence.retository.ClienteRepository;
import br.com.soat8.techchallenge.adapter.out.persistence.entity.CustomerEntity;
import br.com.soat8.techchallenge.core.port.out.SaveCustomerPort;
import br.com.soat8.techchallenge.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerPersistenceAdapter implements SaveCustomerPort {

    private final ClienteRepository clienteRepository;

    public CustomerPersistenceAdapter(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void saveCustomer(Customer customer) {
        CustomerEntity clienteJpaEntity = new CustomerEntity();
        clienteJpaEntity.setName(customer.getName());
        clienteJpaEntity.setEmailAddress(customer.getEmailAddress());
        clienteJpaEntity.setCpf(customer.getCpf());
        clienteRepository.save(clienteJpaEntity);
    }
}
