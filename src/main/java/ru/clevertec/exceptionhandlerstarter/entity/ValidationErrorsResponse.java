package ru.clevertec.exceptionhandlerstarter.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ValidationErrorsResponse(String errorCode,
                                       List<Violation> violations) {
}
