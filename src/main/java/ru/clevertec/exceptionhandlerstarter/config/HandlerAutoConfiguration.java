package ru.clevertec.exceptionhandlerstarter.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.exceptionhandlerstarter.handler.NewsManagementSystemExceptionHandler;

/**
 * Configuration class for exception handling in the News Management System.
 * Configures and initializes exception handling based on conditions.
 *
 * @author Sergey Leshkevich
 * @version 1.0
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(HandlerProperties.class)
@ConditionalOnClass(HandlerProperties.class)
@ConditionalOnProperty(prefix = "exception.handling", name = "enabled", havingValue = "true")
public class HandlerAutoConfiguration {

    /**
     * Initialization method called after bean creation.
     * Logs a message indicating that HandlerAutoConfiguration has been initialized.
     */
    @PostConstruct
    void init() {
        log.info("HandlerAutoConfiguration initialized");
    }

    /**
     * Bean creation method for NewsManagementSystemExceptionHandler.
     * Conditional on the absence of a bean of type NewsManagementSystemExceptionHandler.
     *
     * @return The NewsManagementSystemExceptionHandler bean.
     */
    @Bean
    @ConditionalOnMissingBean(NewsManagementSystemExceptionHandler.class)
    public NewsManagementSystemExceptionHandler newsServiceExceptionHandler() {
        return new NewsManagementSystemExceptionHandler();
    }
}
