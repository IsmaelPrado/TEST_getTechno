package com.cristianprado.controller;

import com.cristianprado.dto.request.factura.FacturaBuscarRequest;
import com.cristianprado.dto.request.factura.FacturaRequest;
import com.cristianprado.dto.response.*;
import com.cristianprado.dto.response.factura.FacturaResponse;
import com.cristianprado.dto.response.factura.FacturasPorPersonaResponse;
import com.cristianprado.service.VentasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
@Tag(name = "Facturas", description = "Operaciones relacionadas con la gestión de facturas")
public class FacturaRestService {

    private final VentasService ventasService;

    @Operation(summary = "Guardar factura", description = "Registra una nueva factura en el sistema")
    @PostMapping
    public ResponseEntity<ApiResponse<FacturaResponse>> guardarFactura(@RequestBody @Valid FacturaRequest request) {
        FacturaResponse response = ventasService.storeFactura(request);

        return ResponseEntity.ok(ApiResponse.<FacturaResponse>builder()
                .success(true)
                .message("Factura guardada correctamente")
                .code(ApiStatusCode.SUCCESS.getCode())
                .data(response)
                .build());
    }

    @Operation(summary = "Buscar facturas por persona", description = "Obtiene una lista paginada de facturas asociadas a una persona")
    @PostMapping("/buscar")
    public ResponseEntity<ApiResponse<FacturasPorPersonaResponse>> obtenerFacturasPorPersona(@RequestBody @Valid FacturaBuscarRequest request) {
        FacturasPorPersonaResponse response = ventasService.findFacturasByPersonaIdentificacion(request);

        return ResponseEntity.ok(ApiResponse.<FacturasPorPersonaResponse>builder()
                .success(true)
                .message("Facturas obtenidas correctamente")
                .code(ApiStatusCode.SUCCESS.getCode())
                .data(response)
                .build());
    }
}
