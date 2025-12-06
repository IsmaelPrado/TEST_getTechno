package com.cristianprado.service.impl;

import com.cristianprado.dto.request.factura.FacturaBuscarRequest;
import com.cristianprado.dto.request.factura.FacturaRequest;
import com.cristianprado.dto.response.factura.FacturaResponse;
import com.cristianprado.dto.response.factura.FacturaSimpleResponse;
import com.cristianprado.dto.response.factura.FacturasPorPersonaResponse;
import com.cristianprado.dto.response.persona.PersonaResponse;
import com.cristianprado.model.Factura;
import com.cristianprado.model.Persona;
import com.cristianprado.repository.FacturaRepository;
import com.cristianprado.service.DirectorioService;
import com.cristianprado.service.VentasService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VentasServiceImpl implements VentasService {

    private final FacturaRepository facturaRepository;
    private final DirectorioService directorioService; // para obtener persona
    private static final Logger logger = LoggerFactory.getLogger(VentasServiceImpl.class);

    @Override
    public FacturaResponse storeFactura(FacturaRequest request) {
        logger.info("Guardando factura: {}", request);

        Persona persona = directorioService.findPersonaEntityByIdentificacion(request.getPersonaIdentificacion());
        // Crear entidad Factura
        Factura factura = new Factura();
        factura.setFecha(request.getFecha());
        factura.setMonto(request.getMonto());
        factura.setPersona(persona);

        Factura saved = facturaRepository.save(factura);

        // Convertir a FacturaResponse
        return FacturaResponse.builder()
                .id(saved.getId())
                .fecha(saved.getFecha())
                .monto(saved.getMonto())
                .persona(PersonaResponse.builder()
                        .id(persona.getId())
                        .nombre(persona.getNombre())
                        .apellidoPaterno(persona.getApellidoPaterno())
                        .apellidoMaterno(persona.getApellidoMaterno())
                        .identificacion(persona.getIdentificacion())
                        .build())
                .build();
    }

    @Override
    public FacturasPorPersonaResponse findFacturasByPersonaIdentificacion(FacturaBuscarRequest request) {
        logger.info("Buscando facturas de la persona con identificación: {}", request.getPersonaIdentificacion());

        // Buscar persona
        Persona persona = directorioService.findPersonaEntityByIdentificacion(request.getPersonaIdentificacion());

        // Paginación
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Factura> facturaPage = facturaRepository.findByPersona(persona, pageable);

        // Mapear las facturas
        List<FacturaSimpleResponse> facturas = facturaPage.getContent().stream()
                .map(f -> FacturaSimpleResponse.builder()
                        .id(f.getId())
                        .fecha(f.getFecha())
                        .monto(f.getMonto())
                        .build())
                .toList();

        // Construir la respuesta final
        return FacturasPorPersonaResponse.builder()
                .persona(PersonaResponse.builder()
                        .id(persona.getId())
                        .nombre(persona.getNombre())
                        .apellidoPaterno(persona.getApellidoPaterno())
                        .apellidoMaterno(persona.getApellidoMaterno())
                        .identificacion(persona.getIdentificacion())
                        .build())
                .facturas(facturas)
                .pageNumber(facturaPage.getNumber())
                .pageSize(facturaPage.getSize())
                .totalElements(facturaPage.getTotalElements())
                .totalPages(facturaPage.getTotalPages())
                .last(facturaPage.isLast())
                .build();
    }


}
