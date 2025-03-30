package com.leang.homework003.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    //     Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(MethodArgumentNotValidException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Validation Failed");
        problemDetail.setProperty("timestamp", LocalDateTime.now());

        // Collect field validation errors
        Map<String, String> errors = new HashMap<>();
        int blankFieldErrors = 0;

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());

            // Check if the error is specifically "must not be blank"
            if ("must not be blank".equals(fieldError.getDefaultMessage())) {
                blankFieldErrors++;
            }
        }

        // Dynamically count all validated fields in the target class
        Class<?> targetClass = Objects.requireNonNull(ex.getTarget()).getClass();
        int totalFields = countValidatedFields(targetClass);

        // If all validated fields are blank, replace with a generic message
        if (blankFieldErrors == totalFields && totalFields > 0) {
            errors.clear();
            String requestKey = targetClass.getSimpleName().replaceAll("Request$", "").toLowerCase() + "Request";
            errors.put(requestKey, "must not be blank");
        }

        // Add errors to ProblemDetail as extra properties
        problemDetail.setProperty("errors", errors);

        return problemDetail;
    }


    @ExceptionHandler(HandlerMethodValidationException.class)
    public ProblemDetail handleMethodValidationException(HandlerMethodValidationException e) {
        Map<String, String> errors = new HashMap<>();

        // Loop through each invalid parameter validation result
        e.getParameterValidationResults().forEach(parameterError -> {
            String paramName = parameterError.getMethodParameter().getParameterName(); // Get parameter name

            // Loop through each validation error message for this parameter
            for (var messageError : parameterError.getResolvableErrors()) {
                errors.put(paramName, messageError.getDefaultMessage()); // Store error message
            }
        });

        // Create structured ProblemDetail response
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Method Parameter Validation Failed");
        problemDetail.setProperties(Map.of("timestamp", LocalDateTime.now(), "errors", errors // Attach validation errors
        ));

        return problemDetail;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleDeserializationErrors(HttpMessageNotReadableException ex) {
        // extract the invalid fields
        String fieldName = extractFieldNameFromException(ex);

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Validation Failed");
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        problemDetail.setProperty("error", fieldName + " has an invalid format");


        return problemDetail;
    }


    private String extractFieldNameFromException(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        if (cause instanceof JsonMappingException) {
            JsonMappingException jsonMappingException = (JsonMappingException) cause;
            return jsonMappingException.getPath().stream().map(JsonMappingException.Reference::getFieldName).findFirst().orElse(null);
        }
        return null;
    }

    private int countValidatedFields(Class<?> clazz) {
        int count = 0;
        for (var field : clazz.getDeclaredFields()) {
            // Check if the field has validation annotations
            if (field.isAnnotationPresent(NotNull.class) ||
                    field.isAnnotationPresent(NotBlank.class) ||
                    field.isAnnotationPresent(Size.class)) {
                count++;
            }
        }
        return count;
    }

}
