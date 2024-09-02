package br.com.soat8.techchallenge.entities.exception;

public class CpfNotExistsException extends RuntimeException {
    public CpfNotExistsException(String message) {
        super(message);
    }
}
