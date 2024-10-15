package br.com.soat8.techchallenge.client.utils;


import br.com.soat8.techchallenge.client.adapters.repository.entity.CustomerEntity;
import br.com.soat8.techchallenge.client.controller.DTO.CustomerRequest;
import br.com.soat8.techchallenge.client.core.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(CustomerRequest customerRequest);
    @Mapping( source = "customerId", target = "id")
    Customer toCustomer(CustomerEntity entity);
    @Mapping( source = "id", target = "customerId")
    CustomerEntity toEntity(Customer customer);
}
