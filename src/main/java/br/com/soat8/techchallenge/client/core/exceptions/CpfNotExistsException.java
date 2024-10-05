package br.com.soat8.techchallenge.client.core.exceptions;

public class CpfNotExistsException extends RuntimeException {
    public CpfNotExistsException(String message) {
        super(message);
    }
}
