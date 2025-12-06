package com.cristianprado.dto.response.persona;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonaResponse {
    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String identificacion;
}
