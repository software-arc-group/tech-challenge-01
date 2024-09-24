package br.com.soat8.techchallenge.item.core.usecase;

import br.com.soat8.techchallenge.item.adapter.OrderSnackItemPort;
import br.com.soat8.techchallenge.item.core.exception.ItemIdNotExistsException;
import br.com.soat8.techchallenge.order.core.entities.OrderSnackItem;
import br.com.soat8.techchallenge.order.core.exceptions.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class GetOrderSnackItemByIdService implements GetOrderSnackItemByIdUseCase{
    @Autowired
    OrderSnackItemPort orderSnackItemPort;

    @Override
    public OrderSnackItem getOrderSnackItem(String id) {
        UUID uuid = null;
        try{
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException exception){
            throw new InvalidArgumentException("id: "+ id + " não é um valor válido para um uuid");
        }
        if (!orderSnackItemPort.existsSnackItemById(uuid)){
            throw new ItemIdNotExistsException("ItemId: " + id + " não existe");
        }
        return orderSnackItemPort.getOrderSnackItemById(uuid);
    }
}
