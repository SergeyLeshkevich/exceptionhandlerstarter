package ru.clevertec.exceptionhandlerstarter.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public static EntityNotFoundException of(Class<?> clazz, Object field) {
        return new EntityNotFoundException(clazz.getSimpleName() + " with " + field + " not found");
    }
}
