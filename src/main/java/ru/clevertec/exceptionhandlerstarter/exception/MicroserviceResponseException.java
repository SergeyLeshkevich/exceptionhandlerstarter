package ru.clevertec.exceptionhandlerstarter.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import ru.clevertec.exceptionhandlerstarter.entity.IncorrectData;

@Getter
public class MicroserviceResponseException extends RuntimeException {

    private IncorrectData incorrectData;
    private HttpStatus status;

    public MicroserviceResponseException(IncorrectData incorrectData, HttpStatus status) {
        this.status = status;
        this.incorrectData = incorrectData;
    }
}
