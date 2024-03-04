package ru.clevertec.exceptionhandlerstarter.exception;

public class UniqueUsernameException extends RuntimeException {

    public UniqueUsernameException(String message) {
        super(message);
    }

}
