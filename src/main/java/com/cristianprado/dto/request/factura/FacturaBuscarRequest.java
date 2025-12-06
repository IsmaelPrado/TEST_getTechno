package com.cristianprado.dto.request.factura;

import com.cristianprado.dto.request.PageableRequest;
import lombok.Data;

@Data
public class FacturaBuscarRequest extends PageableRequest {
    private String personaIdentificacion;
}
