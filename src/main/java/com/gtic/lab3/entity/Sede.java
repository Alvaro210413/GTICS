package com.gtic.lab3.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "sedes")
public class Sede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsede")
    private Integer idSede;

    @Column(name = "nombreSede", length = 45)
    private String nombreSede;

    @Column(name = "direccion", length = 45)
    private String direccion;

    @OneToMany(mappedBy = "sede")
    private List<Trabajador> trabajadores;

    public Integer getIdSede() { return idSede; }
    public void setIdSede(Integer idSede) { this.idSede = idSede; }
    public String getNombreSede() { return nombreSede; }
    public void setNombreSede(String nombreSede) { this.nombreSede = nombreSede; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public List<Trabajador> getTrabajadores() { return trabajadores; }
    public void setTrabajadores(List<Trabajador> trabajadores) { this.trabajadores = trabajadores; }
}