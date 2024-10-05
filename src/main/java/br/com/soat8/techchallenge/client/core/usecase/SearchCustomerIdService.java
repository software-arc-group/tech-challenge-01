package br.com.soat8.techchallenge.client.core.usecase;

import br.com.soat8.techchallenge.client.adapters.repository.CustomerGateway;
import br.com.soat8.techchallenge.client.core.entities.Customer;
import br.com.soat8.techchallenge.client.core.exceptions.CustomerIdNotExistsException;
import br.com.soat8.techchallenge.client.core.usecase.interfaces.SearchCustomerIdUseCase;
import br.com.soat8.techchallenge.order.core.exceptions.InvalidArgumentException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SearchCustomerIdService implements SearchCustomerIdUseCase {
    @Autowired
    private final CustomerGateway customerGateway;

    @Override
    public Customer searchById(@Valid @NotBlank String id) {
        UUID uuid = null;
        try{
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException exception){
            throw new InvalidArgumentException("id: "+ id + " não é um valor válido para um uuid");
        }
        if(!customerGateway.findByCustomerId(uuid)){
            throw new CustomerIdNotExistsException("CustomerId não existe");
        }
        return customerGateway.getCustomerById(uuid);
    }
}
