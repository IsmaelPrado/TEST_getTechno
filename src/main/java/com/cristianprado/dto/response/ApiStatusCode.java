// ApiStatusCode.java
package com.cristianprado.dto.response;

public enum ApiStatusCode {
    SUCCESS(0, "Operación exitosa"),
    PERSONA_NO_ENCONTRADA(1001, "Persona no encontrada"),
    FACTURA_NO_ENCONTRADA(1002, "Factura no encontrada"),
    VALIDACION_FALLIDA(1003, "Error de validación"),
    ERROR_INTERNO(2000, "Error interno del servidor");

    private final int code;
    private final String defaultMessage;

    ApiStatusCode(int code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public int getCode() {
        return code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
