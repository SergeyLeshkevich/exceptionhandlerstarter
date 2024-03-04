package ru.clevertec.exceptionhandlerstarter.exception;

public class NoAuthorizationException extends RuntimeException{

    public NoAuthorizationException(String message) {
        super(message);
    }
}
