package br.com.soat8.techchallenge.entities.exception;

public class IncompleteFieldsException extends RuntimeException {
    public IncompleteFieldsException(String message) {
        super(message);
    }
}
