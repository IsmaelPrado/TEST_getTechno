package com.cristianprado.dto.response.factura;

import com.cristianprado.dto.response.persona.PersonaResponse;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class FacturaResponse {
    private Long id;
    private LocalDate fecha;
    private BigDecimal monto;
    private PersonaResponse persona;
}

