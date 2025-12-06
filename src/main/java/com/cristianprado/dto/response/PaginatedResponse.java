package com.cristianprado.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginatedResponse<T> {
    private List<T> content;       // Los elementos de la página
    private int pageNumber;        // Número de página actual
    private int pageSize;          // Tamaño de página
    private long totalElements;    // Total de elementos en la consulta
    private int totalPages;        // Total de páginas
    private boolean last;          // Si es la última página
}
