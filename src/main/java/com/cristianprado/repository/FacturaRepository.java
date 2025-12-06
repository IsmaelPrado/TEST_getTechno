package com.cristianprado.repository;

import com.cristianprado.model.Factura;
import com.cristianprado.model.Persona;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
    Page<Factura> findByPersona(Persona persona, Pageable pageable);
}
