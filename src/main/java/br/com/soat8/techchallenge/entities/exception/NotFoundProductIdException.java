package br.com.soat8.techchallenge.entities.exception;

public class NotFoundProductIdException extends RuntimeException {
    public NotFoundProductIdException(String message) {
        super(message);
    }
}
