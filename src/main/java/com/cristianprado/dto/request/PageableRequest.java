package com.cristianprado.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PageableRequest {
    @Min(value = 0, message = "El número de página no puede ser negativo")
    private int page = 0;

    @Min(value = 1, message = "El tamaño de página debe ser al menos 1")
    private int size = 10;
}
