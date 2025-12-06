package com.cristianprado.service.impl;

import com.cristianprado.dto.request.persona.PersonaBuscarRequest;
import com.cristianprado.dto.request.persona.PersonaEliminarRequest;
import com.cristianprado.dto.request.persona.PersonaRequest;
import com.cristianprado.dto.request.persona.PersonasBuscarRequest;
import com.cristianprado.dto.response.PaginatedResponse;
import com.cristianprado.dto.response.persona.PersonaResponse;
import com.cristianprado.exception.RecursoNoEncontradoException;
import com.cristianprado.model.Persona;
import com.cristianprado.repository.PersonaRepository;
import com.cristianprado.service.DirectorioService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorioServiceImpl implements DirectorioService {

    private final PersonaRepository personaRepository;
    private static final Logger logger = LoggerFactory.getLogger(DirectorioServiceImpl.class);

    @Override
    public PersonaResponse storePersona(PersonaRequest request) {
        logger.info("Guardando persona: {}", request);

        Persona persona = Persona.builder()
                .nombre(request.getNombre())
                .apellidoPaterno(request.getApellidoPaterno())
                .apellidoMaterno(request.getApellidoMaterno())
                .identificacion(request.getIdentificacion())
                .build();

        Persona saved = personaRepository.save(persona);

        return PersonaResponse.builder()
                .id(saved.getId())
                .nombre(saved.getNombre())
                .apellidoPaterno(saved.getApellidoPaterno())
                .apellidoMaterno(saved.getApellidoMaterno())
                .identificacion(saved.getIdentificacion())
                .build();
    }

    @Override
    public PersonaResponse findPersonaByIdentificacion(PersonaBuscarRequest request) {
        logger.info("Buscando persona con identificación: {}", request.getIdentificacion());

        Persona persona = personaRepository.findAll().stream()
                .filter(p -> p.getIdentificacion().equals(request.getIdentificacion()))
                .findFirst()
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encontró persona con identificación: " + request.getIdentificacion()));

        return PersonaResponse.builder()
                .id(persona.getId())
                .nombre(persona.getNombre())
                .apellidoPaterno(persona.getApellidoPaterno())
                .apellidoMaterno(persona.getApellidoMaterno())
                .identificacion(persona.getIdentificacion())
                .build();
    }

    @Override
    public PaginatedResponse<PersonaResponse> findPersonas(PersonasBuscarRequest request) {
        logger.info("Obteniendo personas con paginación: page={}, size={}", request.getPage(), request.getSize());

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());

        Page<Persona> personaPage;
        if (request.getIdentificacion() != null && !request.getIdentificacion().isEmpty()) {
            // Filtrar por identificacion si se proporciona
            personaPage = personaRepository.findByIdentificacionContainingIgnoreCase(request.getIdentificacion(), pageable);
        } else {
            personaPage = personaRepository.findAll(pageable);
        }

        List<PersonaResponse> content = personaPage.getContent().stream()
                .map(p -> PersonaResponse.builder()
                        .id(p.getId())
                        .nombre(p.getNombre())
                        .apellidoPaterno(p.getApellidoPaterno())
                        .apellidoMaterno(p.getApellidoMaterno())
                        .identificacion(p.getIdentificacion())
                        .build())
                .toList();

        return PaginatedResponse.<PersonaResponse>builder()
                .content(content)
                .pageNumber(personaPage.getNumber())
                .pageSize(personaPage.getSize())
                .totalElements(personaPage.getTotalElements())
                .totalPages(personaPage.getTotalPages())
                .last(personaPage.isLast())
                .build();
    }

    @Override
    public PersonaResponse deletePersonaByIdentificacion(PersonaEliminarRequest request) {
        logger.info("Eliminando persona con identificación: {}", request.getIdentificacion());

        // Buscar la persona
        Persona persona = personaRepository.findAll().stream()
                .filter(p -> p.getIdentificacion().equals(request.getIdentificacion()))
                .findFirst()
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encontró persona con identificación: " + request.getIdentificacion()));

        // Construir el response antes de eliminar
        PersonaResponse response = PersonaResponse.builder()
                .id(persona.getId())
                .nombre(persona.getNombre())
                .apellidoPaterno(persona.getApellidoPaterno())
                .apellidoMaterno(persona.getApellidoMaterno())
                .identificacion(persona.getIdentificacion())
                .build();

        // Eliminar la persona
        personaRepository.delete(persona);

        // Retornar información de la persona eliminada
        return response;
    }

    @Override
    public Persona findPersonaEntityByIdentificacion(String identificacion) {
        return personaRepository.findAll().stream()
                .filter(p -> p.getIdentificacion().equals(identificacion))
                .findFirst()
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encontró persona con identificación: " + identificacion));
    }
}

