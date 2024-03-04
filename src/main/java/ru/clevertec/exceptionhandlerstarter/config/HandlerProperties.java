package ru.clevertec.exceptionhandlerstarter.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties class for exception handling.
 * Uses Lombok annotations: @Slf4j for logger, @Data for getters, setters, and more.
 * Initialized through Spring's @ConfigurationProperties with prefix "exception.handling".
 *
 * @author Sergey Leshkevich
 * @version 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "exception.handling")
public class HandlerProperties {

    /**
     * Flag indicating whether exception handling is enabled.
     */
    private boolean enabled;

    /**
     * Initialization method called after bean creation.
     * Logs a message indicating that HandlerProperties has been initialized.
     */
    @PostConstruct
    void init() {
        log.info("HandlerProperties initialized: {}", this);
    }
}
