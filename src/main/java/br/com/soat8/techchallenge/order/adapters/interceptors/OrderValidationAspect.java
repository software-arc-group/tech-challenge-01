package br.com.soat8.techchallenge.order.adapters.interceptors;

import br.com.soat8.techchallenge.order.adapters.repository.entities.OrderSnackEntity;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Slf4j
@Component
public class OrderValidationAspect {
    @Around("execution(* br.com.soat8.techchallenge.order.adapters.repository.OrderSnackRepository.findByExternalOrderId(..))")
    public OrderSnackEntity validateOrderRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        log.info("Validating order request");
        Optional<OrderSnackEntity> orderSnackEntity = (Optional<OrderSnackEntity>) result;
        if(orderSnackEntity.isEmpty()) {
            throw new RuntimeException("Order not found for the provided externalOrderId");
        }
        return orderSnackEntity.get();

    }
}
