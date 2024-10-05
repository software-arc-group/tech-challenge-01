package br.com.soat8.techchallenge.product.core.usecase;

import br.com.soat8.techchallenge.product.adapters.repository.ProductPort;
import br.com.soat8.techchallenge.product.core.exceptions.NotFoundProductException;
import br.com.soat8.techchallenge.product.core.usecase.interfaces.RemoveProductUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RemoveProductService implements RemoveProductUseCase {
    @Autowired
    private final ProductPort productPort;

    @Override
    public void removeProduct(UUID productId) {
        notFoundProduct(productId);
        productPort.removeProduct(productId);
    }

    private void notFoundProduct(UUID productId) {
        if (!productPort.findById(productId)) {
            throw new NotFoundProductException("This product does not exist Id: " + productId);
        }
    }
}
