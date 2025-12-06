package com.cristianprado.exception;

import com.cristianprado.dto.response.ApiResponse;
import com.cristianprado.dto.response.ApiStatusCode;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Manejar excepciones personalizadas
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ApiResponse<Object> handleNotFoundException(RecursoNoEncontradoException ex) {
        logger.error("Recurso no encontrado: {}", ex.getMessage());
        return ApiResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .code(ApiStatusCode.PERSONA_NO_ENCONTRADA.getCode())
                .data(null)
                .build();
    }

    // Manejar errores de validación @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Object> handleValidationException(MethodArgumentNotValidException ex) {
        // Iterar sobre todos los errores de validación
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            logger.error("Error de validación - Campo: {}, Valor recibido: {}, Mensaje: {}",
                    error.getField(),
                    error.getRejectedValue(),
                    error.getDefaultMessage());
        });

        // Tomar el primer mensaje para la respuesta
        String mensaje = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        return ApiResponse.builder()
                .success(false)
                .message(mensaje)
                .code(ApiStatusCode.VALIDACION_FALLIDA.getCode())
                .data(null)
                .build();
    }

    // Manejar JSON inválido o tipo de dato incorrecto
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResponse<Object> handleInvalidJson(HttpMessageNotReadableException ex) {
        // Verificar si la causa es JsonParseException
        if (ex.getCause() instanceof JsonParseException) {
            logger.warn("JSON inválido recibido: {}", ex.getMessage()); // Log limpio, nivel WARN
            return ApiResponse.builder()
                    .success(false)
                    .message("JSON inválido o tipo de dato incorrecto")
                    .code(ApiStatusCode.VALIDACION_FALLIDA.getCode())
                    .data(null)
                    .build();
        }

        // Otros casos de HttpMessageNotReadable
        logger.warn("Request no legible: {}", ex.getMessage());
        return ApiResponse.builder()
                .success(false)
                .message("Request inválido")
                .code(ApiStatusCode.VALIDACION_FALLIDA.getCode())
                .data(null)
                .build();
    }

    // Manejar excepciones genéricas
    @ExceptionHandler(Exception.class)
    public ApiResponse<Object> handleGenericException(Exception ex) {
        logger.error("Error inesperado: {}", ex.getMessage(), ex);
        return ApiResponse.builder()
                .success(false)
                .message("Ocurrió un error inesperado: " + ex.getMessage())
                .code(ApiStatusCode.ERROR_INTERNO.getCode())
                .data(null)
                .build();
    }
}
