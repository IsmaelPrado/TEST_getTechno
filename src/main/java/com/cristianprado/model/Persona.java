package com.cristianprado.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido_paterno", nullable = false)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", nullable = true)
    private String apellidoMaterno; // opcional

    @Column(name = "identificacion", nullable = false, unique = true)
    private String identificacion;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Factura> facturas = new ArrayList<>();

    // Métodos para mantener relación bidireccional
    public void addFactura(Factura factura) {
        facturas.add(factura);
        factura.setPersona(this);
    }

    public void removeFactura(Factura factura) {
        facturas.remove(factura);
        factura.setPersona(null);
    }
}
