package com.cristianprado.service;

import com.cristianprado.dto.request.factura.FacturaBuscarRequest;
import com.cristianprado.dto.request.factura.FacturaRequest;
import com.cristianprado.dto.response.factura.FacturaResponse;
import com.cristianprado.dto.response.factura.FacturasPorPersonaResponse;

public interface VentasService {

    FacturaResponse storeFactura(FacturaRequest request);

    FacturasPorPersonaResponse findFacturasByPersonaIdentificacion(FacturaBuscarRequest request);
}
