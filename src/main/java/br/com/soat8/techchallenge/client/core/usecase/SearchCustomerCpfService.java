package br.com.soat8.techchallenge.client.core.usecase;

import br.com.soat8.techchallenge.client.adapters.repository.CustomerGateway;
import br.com.soat8.techchallenge.client.core.usecase.interfaces.SearchCpfCustomerUseCase;
import br.com.soat8.techchallenge.client.core.entities.Customer;
import br.com.soat8.techchallenge.client.core.exceptions.CpfNotExistsException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchCustomerCpfService implements SearchCpfCustomerUseCase {
    @Autowired
    private final CustomerGateway customerGateway;

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
}
