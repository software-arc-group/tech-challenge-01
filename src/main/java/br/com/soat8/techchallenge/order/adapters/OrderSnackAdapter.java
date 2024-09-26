package br.com.soat8.techchallenge.adapter.out.persistence.adapter;

import br.com.soat8.techchallenge.adapter.out.persistence.entity.CustomerEntity;
import br.com.soat8.techchallenge.adapter.out.persistence.entity.OrderSnackEntity;
import br.com.soat8.techchallenge.adapter.out.persistence.entity.OrderSnackItemEntity;
import br.com.soat8.techchallenge.adapter.out.persistence.entity.ProductEntity;
import br.com.soat8.techchallenge.adapter.out.persistence.entity.enums.OrderProgress;
import br.com.soat8.techchallenge.adapter.out.persistence.entity.enums.PaymentProgress;
import br.com.soat8.techchallenge.adapter.out.persistence.retository.CustomerRepository;
import br.com.soat8.techchallenge.adapter.out.persistence.retository.OrderSnackRepository;
import br.com.soat8.techchallenge.adapter.out.persistence.specification.OrderSnackSpecification;
import br.com.soat8.techchallenge.core.port.out.OrderSnackPort;
import br.com.soat8.techchallenge.domain.OrderSnack;
import br.com.soat8.techchallenge.domain.OrderSnackItem;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderSnackAdapter implements OrderSnackPort {

    private final OrderSnackRepository orderSnackRepository;
    private final CustomerRepository customerRepository;

    public OrderSnackAdapter(OrderSnackRepository orderSnackRepository, CustomerRepository customerRepository) {
        this.orderSnackRepository = orderSnackRepository;
        this.customerRepository = customerRepository;
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
                .createdAt(LocalDateTime.now())
                .customerName(orderSnack.getCustomer().getName())
                .cpf(orderSnack.getCustomer().getCpf())
                .items(orderSnack.getOrderSnackItems()
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

    @Transactional
    @Override
    public void saveOrderSnack(OrderSnack orderSnack, UUID externalOrderId) {
        if(orderSnack == null){
            return;
        }
        CustomerEntity customer = customerRepository.findById(orderSnack.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        OrderSnackEntity orderSnackEntity =  OrderSnackEntity.builder()
                .progress(OrderProgress.RECEIVED)
                .totalPrice(orderSnack.getTotalPrice())
                .createdAt(LocalDateTime.now())
                .customer(customer)
                .externalOrderId(String.valueOf(externalOrderId))
                .paymentProgress(PaymentProgress.OPPENED)
                .build();

        List<OrderSnackItemEntity> orderSnackItems = orderSnack.getItems().stream()
                .map(item -> {
                    OrderSnackItemEntity itemEntity = buildOrderSnackItemEntity(item);
                    itemEntity.setOrderSnack(orderSnackEntity);
                    return itemEntity;
                })
                .toList();

        orderSnackEntity.setOrderSnackItems(orderSnackItems);
        orderSnackRepository.save(orderSnackEntity);
    }

    private OrderSnackItemEntity buildOrderSnackItemEntity(OrderSnackItem orderSnackItem){
        ProductEntity product = ProductEntity.builder()
                .productId(orderSnackItem.getProductId())
                .build();

        return OrderSnackItemEntity
                .builder()
                .product(product)
                .quantity(orderSnackItem.getQuantity())
                .amount(orderSnackItem.getPrice())
                .build();
    }
}
