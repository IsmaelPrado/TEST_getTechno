package com.cristianprado.dto.request.persona;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PersonaEliminarRequest {

    @NotBlank(message = "La identificación es obligatoria")
    private String identificacion;
}
