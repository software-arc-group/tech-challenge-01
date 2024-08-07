package br.com.soat8.techchallenge.adapter.out.persistence.adapter;

import br.com.soat8.techchallenge.adapter.out.persistence.entity.OrderSnackEntity;
import br.com.soat8.techchallenge.adapter.out.persistence.entity.OrderSnackItemEntity;
import br.com.soat8.techchallenge.adapter.out.persistence.entity.enums.OrderProgress;
import br.com.soat8.techchallenge.adapter.out.persistence.retository.OrderSnackRepository;
import br.com.soat8.techchallenge.adapter.out.persistence.specification.OrderSnackSpecification;
import br.com.soat8.techchallenge.core.port.out.OrderSnackPort;
import br.com.soat8.techchallenge.domain.OrderSnack;
import br.com.soat8.techchallenge.domain.OrderSnackItem;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderSnackAdapter implements OrderSnackPort {

    private final OrderSnackRepository orderSnackRepository;

    public OrderSnackAdapter(OrderSnackRepository orderSnackRepository) {
        this.orderSnackRepository = orderSnackRepository;
    }

    @Override
    public List<OrderSnack> listOrderSnack(OrderProgress progress, String cpf) {
        Specification<OrderSnackEntity> spec = Specification
                .where(OrderSnackSpecification.hasProgress(progress))
                .and(OrderSnackSpecification.hasCustomerCpf(cpf));

        return orderSnackRepository.findAll(spec)
                .stream()
                .map(OrderSnackAdapter::buildOrderSnack)
                .collect(Collectors.toList());
    }

    private static OrderSnack buildOrderSnack(OrderSnackEntity orderSnack) {

        return OrderSnack.builder()
                .orderSnackId(orderSnack.getOrderSnackId())
                .progress(orderSnack.getProgress().name())
                .createdAt(orderSnack.getCreatedAt())
                .customerName(orderSnack.getCustomer().getName())
                .cpf(orderSnack.getCustomer().getCpf())
                .orderSnackItems(orderSnack.getOrderSnackItems()
                        .stream().map(OrderSnackAdapter::buildOrderSnackItem)
                        .collect(Collectors.toList()))
                .build();
    }

    private static OrderSnackItem buildOrderSnackItem(OrderSnackItemEntity item) {
        return OrderSnackItem.builder()
                .orderSnackItemId(item.getOrderSnackItemId())
                .amount(item.getAmount())
                .productId(item.getProduct().getProductId())
                .productName(item.getProduct().getName())
                .build();

    }
}
