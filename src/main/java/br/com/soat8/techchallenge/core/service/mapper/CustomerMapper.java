package br.com.soat8.techchallenge.core.service.mapper;


import br.com.soat8.techchallenge.cliente.infraesctructure.out.persistence.CustomerEntity;
import br.com.soat8.techchallenge.cliente.controller.requests.CustomerRequest;
import br.com.soat8.techchallenge.entities.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer toCustomer(CustomerRequest customerRequest);
    Customer toCustomer(CustomerEntity entity);
    CustomerEntity toEntity(Customer customer);
}
