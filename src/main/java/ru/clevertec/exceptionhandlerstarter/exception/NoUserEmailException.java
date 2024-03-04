package ru.clevertec.exceptionhandlerstarter.exception;

public class NoUserEmailException extends RuntimeException {

    public NoUserEmailException(String message) {
        super(message);
    }

}
