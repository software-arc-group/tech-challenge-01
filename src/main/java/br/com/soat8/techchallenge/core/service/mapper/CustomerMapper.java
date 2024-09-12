package br.com.soat8.techchallenge.core.service.mapper;


import br.com.soat8.techchallenge.client.adapters.repository.entity.CustomerEntity;
import br.com.soat8.techchallenge.client.controller.DTO.CustomerRequest;
import br.com.soat8.techchallenge.client.core.entities.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer toCustomer(CustomerRequest customerRequest);
    Customer toCustomer(CustomerEntity entity);
    CustomerEntity toEntity(Customer customer);
}
