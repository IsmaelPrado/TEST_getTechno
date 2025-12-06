package com.cristianprado.service;

import com.cristianprado.dto.request.persona.PersonaEliminarRequest;
import com.cristianprado.dto.response.persona.PersonaResponse;
import com.cristianprado.exception.RecursoNoEncontradoException;
import com.cristianprado.model.Persona;
import com.cristianprado.repository.PersonaRepository;
import com.cristianprado.service.impl.DirectorioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DirectorioServiceTest {

    @Mock
    private PersonaRepository personaRepository;

    @InjectMocks
    private DirectorioServiceImpl directorioService; // tu implementación concreta

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void eliminarPersona_existente_retornaPersonaResponse() {
        // Arrange
        Persona persona = new Persona();
        persona.setId(1L);
        persona.setNombre("Ismael");
        persona.setApellidoPaterno("Prado");
        persona.setApellidoMaterno("Salazar");
        persona.setIdentificacion("12345");

        when(personaRepository.findAll()).thenReturn(Arrays.asList(persona));

        PersonaEliminarRequest request = new PersonaEliminarRequest();
        request.setIdentificacion("12345");

        // Act
        PersonaResponse response = directorioService.deletePersonaByIdentificacion(request);

        // Assert
        assertNotNull(response);
        assertEquals("Ismael", response.getNombre());
        verify(personaRepository, times(1)).delete(persona);
    }

    @Test
    void eliminarPersona_noExistente_lanzaExcepcion() {
        // Arrange
        when(personaRepository.findAll()).thenReturn(Arrays.asList());
        PersonaEliminarRequest request = new PersonaEliminarRequest();
        request.setIdentificacion("99999");

        // Act & Assert
        assertThrows(RecursoNoEncontradoException.class,
                () -> directorioService.deletePersonaByIdentificacion(request));
    }

    @Test
    void buscarPersona_porIdentificacion_retornaPersona() {
        // Arrange
        Persona persona = new Persona();
        persona.setId(2L);
        persona.setNombre("Juan");
        persona.setApellidoPaterno("Lopez");
        persona.setIdentificacion("54321");

        when(personaRepository.findAll()).thenReturn(Arrays.asList(persona));

        // Act
        Persona result = directorioService.findPersonaEntityByIdentificacion("54321");

        // Assert
        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
    }
}
