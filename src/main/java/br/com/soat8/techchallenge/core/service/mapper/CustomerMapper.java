package br.com.soat8.techchallenge.core.service.mapper;


import br.com.soat8.techchallenge.adapter.out.persistence.entity.CustomerEntity;
import br.com.soat8.techchallenge.application.controller.request.CustomerRequest;
import br.com.soat8.techchallenge.entities.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer toCustomer(CustomerRequest customerRequest);
    Customer toCustomer(CustomerEntity entity);
    CustomerEntity toEntity(Customer customer);
}
