package com.cristianprado.repository;

import com.cristianprado.model.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Page<Persona> findByIdentificacionContainingIgnoreCase(String identificacion, Pageable pageable);
}
