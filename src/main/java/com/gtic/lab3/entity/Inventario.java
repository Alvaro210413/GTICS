package com.gtic.lab3.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "inventario")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idinventario")
    private Integer idInventario;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "numeroserie", length = 45)
    private String numeroSerie;

    @ManyToOne
    @JoinColumn(name = "idsede")
    private Sede sede;

    @ManyToOne
    @JoinColumn(name = "idmarca")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "idtipo")
    private Tipo tipo;

    @Column(name = "estado", length = 45)
    private String estado;

    public Integer getIdInventario() { return idInventario; }
    public void setIdInventario(Integer idInventario) { this.idInventario = idInventario; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getNumeroSerie() { return numeroSerie; }
    public void setNumeroSerie(String numeroSerie) { this.numeroSerie = numeroSerie; }
    public Sede getSede() { return sede; }
    public void setSede(Sede sede) { this.sede = sede; }
    public Marca getMarca() { return marca; }
    public void setMarca(Marca marca) { this.marca = marca; }
    public Tipo getTipo() { return tipo; }
    public void setTipo(Tipo tipo) { this.tipo = tipo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}