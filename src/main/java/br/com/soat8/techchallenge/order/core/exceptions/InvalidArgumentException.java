package br.com.soat8.techchallenge.order.core.exceptions;

public class InvalidArgumentException extends RuntimeException{
    public InvalidArgumentException(String message) {
        super(message);
    }
}
