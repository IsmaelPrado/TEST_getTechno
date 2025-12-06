package com.cristianprado.dto.response.factura;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class FacturaSimpleResponse {
    private Long id;
    private LocalDate fecha;
    private BigDecimal monto;
}
