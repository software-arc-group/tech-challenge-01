package br.com.soat8.techchallenge.order.core.usecase;

import br.com.soat8.techchallenge.client.controller.DTO.CustomerRequest;
import br.com.soat8.techchallenge.client.core.entities.Customer;
import br.com.soat8.techchallenge.order.adapters.repository.OrderSnackPort;
import br.com.soat8.techchallenge.order.core.entities.OrderSnack;
import br.com.soat8.techchallenge.order.core.entities.enums.OrderProgress;
import br.com.soat8.techchallenge.order.core.usecase.interfaces.UpdateOrderSnackProgressUseCase;
import br.com.soat8.techchallenge.order.utils.OrderProgressMapper;
import br.com.soat8.techchallenge.product.controller.DTO.ProductRequest;
import br.com.soat8.techchallenge.product.core.entities.Product;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UpdateOrderSnackProgressService implements UpdateOrderSnackProgressUseCase {

    @Autowired
    private final OrderSnackPort orderSnackPort;

    @Autowired
    private final OrderProgressMapper progressMapper;

    @Override
    public void updateOrderSnackProgress(OrderProgress orderProgress, UUID orderSnackId) {
        orderSnackPort.updateOrderSnackProgress(orderProgress, orderSnackId);

        //OrderSnack orderSnack = progressMapper.s

    }


/*

    @Override
    public Customer saveCustomer(CustomerRequest customerRequest) {
        existCpf(customerRequest.getCpf());
        existEmail(customerRequest.getEmailAddress());
        Customer customer= mapper.toCustomer(customerRequest);
        return customerGateway.saveCustomer(customer);
    }

    @Override
    public void saveProduct(ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        product.setCategory(getProductCategory(productRequest.getCategoryId()));
        productPort.saveOrUpdate(product);
    }

*/

}
