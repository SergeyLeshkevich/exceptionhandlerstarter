package ru.clevertec.exceptionhandlerstarter.handler;

import jakarta.persistence.PrePersist;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.clevertec.exceptionhandlerstarter.exception.AccessDeniedException;
import ru.clevertec.exceptionhandlerstarter.exception.MicroserviceResponseException;
import ru.clevertec.exceptionhandlerstarter.exception.EntityNotFoundException;
import ru.clevertec.exceptionhandlerstarter.exception.NoAuthorizationException;
import ru.clevertec.exceptionhandlerstarter.exception.ParsJsonException;
import ru.clevertec.exceptionhandlerstarter.exception.UniqueUsernameException;
import ru.clevertec.exceptionhandlerstarter.entity.IncorrectData;
import ru.clevertec.exceptionhandlerstarter.entity.ValidationErrorsResponse;
import ru.clevertec.exceptionhandlerstarter.entity.Violation;

import java.util.List;

/**
 * Global exception handler for the News Management System.
 *
 * @author Sergey Leshkevich
 * @version 1.0
 */
@Slf4j
@RestControllerAdvice
public class NewsManagementSystemExceptionHandler {

    @PrePersist()
    public void init() {
        log.info("Init NewsManagementSystemExceptionHandler");
    }

    /**
     * Handles AccessDeniedForThisRoleException and returns a corresponding ResponseEntity.
     *
     * @param exception The AccessDeniedForThisRoleException to handle.
     * @return ResponseEntity with details about the exception and HTTP status FORBIDDEN.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<IncorrectData> accessDeniedForThisRoleException(AccessDeniedException exception) {
        log.info("Calling the accessDeniedForThisRoleException() method");
        return getResponse(exception.getClass().getSimpleName(), exception.getMessage(), HttpStatus.FORBIDDEN);
    }

    /**
     * Handles IllegalArgumentException and returns a corresponding ResponseEntity.
     *
     * @param exception The AccessDeniedForThisRoleException to handle.
     * @return ResponseEntity with details about the exception and HTTP status BAD REQUEST.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<IncorrectData> invalidInputParametersException(IllegalArgumentException exception) {
        log.info("Calling the invalidInputParametersException() method");
        return getResponse(exception.getClass().getSimpleName(), exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles EntityNotFoundException and returns a corresponding ResponseEntity.
     *
     * @param exception The EntityNotFoundException to handle.
     * @return ResponseEntity with details about the exception and HTTP status NOT_FOUND.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<IncorrectData> notFoundException(EntityNotFoundException exception) {
        log.info("Calling the notFoundException() method");
        return getResponse(exception.getClass().getSimpleName(), exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles NoAuthorizationException and returns a corresponding ResponseEntity.
     *
     * @param exception The NoAuthorizationException to handle.
     * @return ResponseEntity with details about the exception and HTTP status UNAUTHORIZED.
     */
    @ExceptionHandler(NoAuthorizationException.class)
    public ResponseEntity<IncorrectData> noAuthorizationException(NoAuthorizationException exception) {
        log.info("Calling the noAuthorizationException() method");
        return getResponse(exception.getClass().getSimpleName(), exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles ConstraintViolationException and returns a ResponseEntity with validation errors.
     *
     * @param exception The ConstraintViolationException to handle.
     * @return ResponseEntity with validation errors and HTTP status CONFLICT.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationErrorsResponse> constraintValidationException(ConstraintViolationException exception) {
        log.info("Calling the constraintValidationException() method");
        List<Violation> violations = exception.getConstraintViolations()
                .stream()
                .map(constraintViolation -> new Violation(constraintViolation.getPropertyPath().toString(),
                        constraintViolation.getMessage()))
                .toList();
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ValidationErrorsResponse(HttpStatus.CONFLICT.toString(), violations));
    }

    /**
     * Handles UniqueEmailException and returns a corresponding ResponseEntity.
     *
     * @param exception The UniqueUsernameException to handle.
     * @return ResponseEntity with details about the exception and HTTP status NOT_ACCEPTABLE.
     */
    @ExceptionHandler(UniqueUsernameException.class)
    public ResponseEntity<IncorrectData> uniqueUsernameException(UniqueUsernameException exception) {
        log.info("Calling the uniqueUsernameException() method");
        return getResponse(exception.getClass().getSimpleName(), exception.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * Handles RuntimeException and returns a corresponding ResponseEntity.
     *
     * @param exception The RuntimeException to handle.
     * @return ResponseEntity with details about the exception and HTTP status INTERNAL_SERVER_ERROR.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<IncorrectData> runtimeException(RuntimeException exception) {
        log.info("Calling the runtimeException() method");
        return getResponse(exception.getClass().getSimpleName(), exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles ParsJsonException and returns a corresponding ResponseEntity.
     *
     * @param exception The ParsJsonException to handle.
     * @return ResponseEntity with details about the exception and HTTP status INTERNAL_SERVER_ERROR.
     */
    @ExceptionHandler(ParsJsonException.class)
    public ResponseEntity<IncorrectData> parsJsonException(ParsJsonException exception) {
        log.info("Calling the parsJsonException() method");
        return getResponse(exception.getClass().getSimpleName(), exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Transforms the exception received from the microservice and returns the corresponding ResponseEntity.
     *
     * @param exception The UserApiClientException to handle.
     * @return ResponseEntity with details about the exception and HTTP status.
     */
    @ExceptionHandler(MicroserviceResponseException.class)
    public ResponseEntity<IncorrectData> microserviceResponseException(MicroserviceResponseException exception) {
        log.info("Calling the microserviceResponseException() method");
        return ResponseEntity.status(exception.getStatus()).body(exception.getIncorrectData());
    }

    /**
     * Utility method to create a ResponseEntity with {@link IncorrectData} based on the provided parameters.
     *
     * @param name    The name of the exception.
     * @param message The exception message.
     * @param status  The HTTP status.
     * @return ResponseEntity with IncorrectData.
     */
    private static ResponseEntity<IncorrectData> getResponse(String name, String message, HttpStatus status) {
        IncorrectData incorrectData = new IncorrectData(name, message, status.toString());
        log.info("Returning status: {}, response: {}", status, incorrectData);
        return ResponseEntity.status(status).body(incorrectData);
    }
}
