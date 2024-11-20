package br.com.soat8.techchallenge.client.adapters.repository.interceptors;

import br.com.soat8.techchallenge.client.adapters.repository.CustomerGateway;
import br.com.soat8.techchallenge.client.adapters.repository.entity.CustomerEntity;
import br.com.soat8.techchallenge.client.controller.DTO.CustomerRequest;
import br.com.soat8.techchallenge.client.core.entities.Customer;
import br.com.soat8.techchallenge.client.core.exceptions.CpfAlreadyExistsException;
import br.com.soat8.techchallenge.client.core.exceptions.CustomerIdNotExistsException;
import br.com.soat8.techchallenge.client.core.exceptions.EmailAlreadyExistsException;
import br.com.soat8.techchallenge.order.core.exceptions.InvalidArgumentException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Slf4j
@Component
public class CustomerValidationAspect {
    @Autowired
    private CustomerGateway customerGateway;

    @Around("execution(* br.com.soat8.techchallenge.client.adapters.repository.CustomerRepository.findById(..)) && args(idAsString)")
    public Object validateCustomerExistsById(ProceedingJoinPoint pjp, String idAsString) throws Throwable {
        log.info("[FIND BY ID] Validating customer exists");
        UUID uuid = null;
        try{
            uuid = UUID.fromString(idAsString);
        } catch (IllegalArgumentException exception){
            throw new InvalidArgumentException("id: "+ idAsString + " não é um valor válido para um uuid");
        }
        if(!customerGateway.findByCustomerId(uuid)){
            throw new CustomerIdNotExistsException("CustomerId não existe");
        }
       return pjp.proceed();
    }

    @Around("execution(* br.com.soat8.techchallenge.client.adapters.repository.CustomerRepository.save(..)) && args(customerEntity)")
    public Object validateCustomerAlredyExists(ProceedingJoinPoint pjp, CustomerEntity customerEntity) throws Throwable {
        log.info("[BEFORE SAVE] Validating customer already exists");
//        Object[] args = pjp.getArgs();
//        CustomerEntity customerRequest = (CustomerEntity) args[0];
        if(!customerGateway.findByCpf(customerEntity.getCpf())){
            throw new CpfAlreadyExistsException("CPF already exists: " + customerEntity.getCpf() );
        }
        if(!customerGateway.findByEmailAddress(customerEntity.getEmailAddress())){
            throw new EmailAlreadyExistsException("Email already exists: " + customerEntity.getEmailAddress());
        }
       return pjp.proceed();
    }

}
