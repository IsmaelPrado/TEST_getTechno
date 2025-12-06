package com.cristianprado.dto.response.factura;

import com.cristianprado.dto.response.persona.PersonaResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FacturasPorPersonaResponse {
    private PersonaResponse persona;
    private List<FacturaSimpleResponse> facturas;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
