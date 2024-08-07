package br.com.soat8.techchallenge.adapter.out.persistence.adapter;

import br.com.soat8.techchallenge.adapter.out.persistence.entity.CustomerEntity;
import br.com.soat8.techchallenge.adapter.out.persistence.entity.OrderSnackEntity;
import br.com.soat8.techchallenge.adapter.out.persistence.entity.OrderSnackItemEntity;
import br.com.soat8.techchallenge.adapter.out.persistence.entity.ProductEntity;
import br.com.soat8.techchallenge.adapter.out.persistence.entity.enums.OrderProgress;
import br.com.soat8.techchallenge.adapter.out.persistence.retository.OrderSnackRepository;
import br.com.soat8.techchallenge.core.port.out.OrderSnackPort;
import br.com.soat8.techchallenge.domain.Customer;
import br.com.soat8.techchallenge.domain.OrderSnack;
import br.com.soat8.techchallenge.domain.OrderSnackItem;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
public class OrderSnackAdapter implements OrderSnackPort {
    private final OrderSnackRepository orderSnackRepository;

    OrderSnackAdapter(OrderSnackRepository orderSnackRepository){
        this.orderSnackRepository = orderSnackRepository;
    }

    @Transactional
    @Override
    public void saveOrderSnack(OrderSnack orderSnack) {
        if(orderSnack == null){
            return;
        }
        CustomerEntity customer = new CustomerEntity();
        customer.setCustomerId(orderSnack.getCustomerId());

        OrderSnackEntity orderSnackEntity =  OrderSnackEntity.builder()
                .progress(OrderProgress.RECEIVED)
                .totalPrice(orderSnack.getTotalPrice())
                .customer(customer)
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
