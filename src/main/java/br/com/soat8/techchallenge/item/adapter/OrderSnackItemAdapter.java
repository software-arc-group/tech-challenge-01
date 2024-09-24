package br.com.soat8.techchallenge.item.adapter;

import br.com.soat8.techchallenge.item.adapter.repository.OrderSnackItemRepository;
import br.com.soat8.techchallenge.item.utils.OrderSnackItemMapper;
import br.com.soat8.techchallenge.order.core.entities.OrderSnackItem;
import br.com.soat8.techchallenge.order.core.exceptions.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderSnackItemAdapter implements OrderSnackItemPort {
    @Autowired
    OrderSnackItemRepository repository;
    @Autowired
    OrderSnackItemMapper mapper;

    @Override
    public OrderSnackItem getOrderSnackItemById(UUID id) {
        return mapper.toOrderSnackItem(repository.findById(id).get());
    }

    @Override
    public Boolean existsSnackItemById(UUID id) {
        return repository.existsById(id);
    }
}
