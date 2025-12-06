package com.cristianprado.dto.request.persona;

import com.cristianprado.dto.request.PageableRequest;
import lombok.Data;

@Data
public class PersonasBuscarRequest extends PageableRequest {
    private String identificacion;
}