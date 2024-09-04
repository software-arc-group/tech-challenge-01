package br.com.soat8.techchallenge.exception;

public class CpfNotExistsException extends RuntimeException {
    public CpfNotExistsException(String message) {
        super(message);
    }
}
