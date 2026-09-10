package com.gtic.lab3.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "trabajadores")
public class Trabajador {
    @Id
    @Column(name = "dni", length = 45)
    private String dni;

    @Column(name = "nombres", length = 45)
    private String nombres;

    @Column(name = "apellidos", length = 45)
    private String apellidos;

    @Column(name = "correo", length = 45)
    private String correo;

    @ManyToOne
    @JoinColumn(name = "idsede")
    private Sede sede;

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public Sede getSede() { return sede; }
    public void setSede(Sede sede) { this.sede = sede; }
}