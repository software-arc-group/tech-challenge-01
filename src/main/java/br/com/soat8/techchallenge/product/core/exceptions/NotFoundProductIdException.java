package br.com.soat8.techchallenge.product.core.exceptions;

public class NotFoundProductIdException extends RuntimeException {
    public NotFoundProductIdException(String message) {
        super(message);
    }
}
