package br.com.soat8.techchallenge.order.adapters.repository;

import br.com.soat8.techchallenge.order.adapters.repository.entities.OrderSnackEntity;
import br.com.soat8.techchallenge.order.utils.OrderSnackMapper;
import br.com.soat8.techchallenge.order.core.entities.enums.OrderProgress;
import br.com.soat8.techchallenge.order.core.entities.OrderSnack;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class OrderSnackAdapter implements OrderSnackPort {

    @Autowired
    private final OrderSnackRepository orderSnackRepository;
    @Autowired
    private final OrderSnackMapper orderSnackMapper;


    @Override
    public List<OrderSnack> listOrderSnack(OrderProgress progress, String cpf) {
        Specification<OrderSnackEntity> spec = Specification
                .where(OrderSnackSpecification.hasProgress(progress))
                .and(OrderSnackSpecification.hasCustomerCpf(cpf));

        return orderSnackRepository.findAll(spec)
                .stream()
                .map(orderSnackMapper::toOrderSnack)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void saveOrderSnack(OrderSnack orderSnack) {
        if(orderSnack == null){
            return;
        }
        OrderSnackEntity orderSnackEntity =  orderSnackMapper.toEntity(orderSnack);
        orderSnackRepository.save(orderSnackEntity);
    }
}
