package com.cristianprado.service;

import com.cristianprado.dto.request.persona.PersonaBuscarRequest;
import com.cristianprado.dto.request.persona.PersonaEliminarRequest;
import com.cristianprado.dto.request.persona.PersonaRequest;
import com.cristianprado.dto.request.persona.PersonasBuscarRequest;
import com.cristianprado.dto.response.PaginatedResponse;
import com.cristianprado.dto.response.persona.PersonaResponse;
import com.cristianprado.model.Persona;


public interface DirectorioService {

    PersonaResponse storePersona(PersonaRequest request);

    PersonaResponse findPersonaByIdentificacion(PersonaBuscarRequest request);

    PaginatedResponse<PersonaResponse> findPersonas(PersonasBuscarRequest request);

    PersonaResponse deletePersonaByIdentificacion(PersonaEliminarRequest request);

    Persona findPersonaEntityByIdentificacion(String identificacion);
}
