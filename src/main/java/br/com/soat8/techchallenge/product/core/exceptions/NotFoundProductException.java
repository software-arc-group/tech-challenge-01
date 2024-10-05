package br.com.soat8.techchallenge.product.core.exceptions;

public class NotFoundProductException extends RuntimeException {
    public NotFoundProductException(String message) {
        super(message);
    }
}
