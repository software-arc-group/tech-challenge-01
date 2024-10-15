package br.com.soat8.techchallenge.client.core.exceptions;

public class CustomerIdNotExistsException extends RuntimeException {
    public CustomerIdNotExistsException(String message) {
        super(message);
    }
}
