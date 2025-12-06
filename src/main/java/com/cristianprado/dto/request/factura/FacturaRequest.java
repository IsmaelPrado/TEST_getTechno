package com.cristianprado.dto.request.factura;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class FacturaRequest {

    @NotNull(message = "La fecha de la factura es obligatoria")
    @PastOrPresent(message = "La fecha de la factura no puede ser futura")
    private LocalDate fecha;

    @NotNull(message = "El monto de la factura es obligatorio")
    @DecimalMin(value = "0.01", inclusive = true, message = "El monto debe ser mayor a 0")
    @Digits(integer = 12, fraction = 2, message = "El monto debe tener máximo 12 dígitos enteros y 2 decimales")
    private BigDecimal monto;

    @NotBlank(message = "La identificación de la persona es obligatoria")
    @Size(max = 20, message = "La identificación de la persona debe tener máximo 20 caracteres")
    private String personaIdentificacion;
}
