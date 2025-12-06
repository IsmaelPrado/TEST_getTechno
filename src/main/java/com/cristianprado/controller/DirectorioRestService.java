package com.cristianprado.controller;

import com.cristianprado.dto.request.persona.PersonaBuscarRequest;
import com.cristianprado.dto.request.persona.PersonaEliminarRequest;
import com.cristianprado.dto.request.persona.PersonaRequest;
import com.cristianprado.dto.request.persona.PersonasBuscarRequest;
import com.cristianprado.dto.response.ApiResponse;
import com.cristianprado.dto.response.ApiStatusCode;
import com.cristianprado.dto.response.PaginatedResponse;
import com.cristianprado.dto.response.persona.PersonaResponse;
import com.cristianprado.service.DirectorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/personas")
@RequiredArgsConstructor
@Tag(name = "Personas", description = "Operaciones relacionadas con el directorio de personas")
public class DirectorioRestService {

    private final DirectorioService directorioService;

    @Operation(summary = "Guardar persona", description = "Crea o actualiza una persona en el directorio")
    @PostMapping
    public ResponseEntity<ApiResponse<PersonaResponse>> guardarPersona(@RequestBody @Valid PersonaRequest request) {
        System.out.println("Recibido request: " + request);
        PersonaResponse persona = directorioService.storePersona(request);

        ApiResponse<PersonaResponse> response = ApiResponse.<PersonaResponse>builder()
                .success(true)
                .message("Persona guardada correctamente")
                .code(ApiStatusCode.SUCCESS.getCode())
                .data(persona)
                .build();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Buscar personas", description = "Obtiene una lista paginada de personas según los criterios de búsqueda")
    @PostMapping("/buscar/personas")
    public ResponseEntity<ApiResponse<PaginatedResponse<PersonaResponse>>> obtenerPersonas(@RequestBody @Valid PersonasBuscarRequest request) {
        PaginatedResponse<PersonaResponse> personas = directorioService.findPersonas(request);

        ApiResponse<PaginatedResponse<PersonaResponse>> response = ApiResponse.<PaginatedResponse<PersonaResponse>>builder()
                .success(true)
                .message("Personas obtenidas correctamente")
                .code(ApiStatusCode.SUCCESS.getCode())
                .data(personas)
                .build();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Obtener persona por identificación", description = "Obtiene los datos de una persona específica usando su identificación")
    @PostMapping("/buscar/persona")
    public ResponseEntity<ApiResponse<PersonaResponse>> obtenerPersonaPorIdentificacion(@RequestBody @Valid PersonaBuscarRequest request) {
        PersonaResponse persona = directorioService.findPersonaByIdentificacion(request);

        ApiResponse<PersonaResponse> response = ApiResponse.<PersonaResponse>builder()
                .success(true)
                .message("Persona obtenida correctamente")
                .code(ApiStatusCode.SUCCESS.getCode())
                .data(persona)
                .build();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Eliminar persona", description = "Elimina una persona del directorio según su identificación")
    @DeleteMapping("/eliminar")
    public ResponseEntity<ApiResponse<PersonaResponse>> eliminarPersona(@RequestBody @Valid PersonaEliminarRequest request) {
        PersonaResponse persona = directorioService.deletePersonaByIdentificacion(request);

        ApiResponse<PersonaResponse> response = ApiResponse.<PersonaResponse>builder()
                .success(true)
                .message("Persona eliminada correctamente")
                .code(ApiStatusCode.SUCCESS.getCode())
                .data(persona)
                .build();

        return ResponseEntity.ok(response);
    }
}


