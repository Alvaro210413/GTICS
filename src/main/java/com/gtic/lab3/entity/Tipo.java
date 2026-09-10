package com.gtic.lab3.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tipos")
public class Tipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtipo")
    private Integer idTipo;

    @Column(name = "nombre", length = 45)
    private String nombre;

    public Integer getIdTipo() { return idTipo; }
    public void setIdTipo(Integer idTipo) { this.idTipo = idTipo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
