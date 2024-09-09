package br.com.soat8.techchallenge.cliente.core.usecase;

import br.com.soat8.techchallenge.cliente.gateway.CustomerGateway;
import br.com.soat8.techchallenge.cliente.core.usecase.interfaces.SearchCpfCustomerUseCase;
import br.com.soat8.techchallenge.entities.Customer;
import br.com.soat8.techchallenge.entities.exception.CpfNotExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchCustomerCpfService implements SearchCpfCustomerUseCase {
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
