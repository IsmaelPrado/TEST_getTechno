package com.cristianprado.dto.request.persona;

import com.cristianprado.dto.request.PageableRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PersonaBuscarRequest extends PageableRequest {
    @NotBlank(message = "La identificación es obligatoria")
    private String identificacion;
}
