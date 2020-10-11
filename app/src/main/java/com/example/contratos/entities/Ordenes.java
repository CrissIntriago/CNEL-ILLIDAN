package com.example.contratos.entities;

import com.example.Model.Persona;
import com.example.utils.Utils;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class Ordenes implements Serializable {
    private Integer id;
    private BigInteger numOrden;
    private BigInteger numfolio;
    private Date fecha_recepcion;
    private Date fecha_aceptacion;
    private String direccion;
    private String servicio;
    private String descripcion;
    private String id_cliente;
    private String nom_cliente;
    private Boolean estado;
    private String usuarioCreacion;
    private short periodo;

    public Ordenes(){
        this.estado = Boolean.TRUE;
        this.periodo = Utils.getAnio(new Date()).shortValue();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getNumOrden() {
        return numOrden;
    }

    public void setNumOrden(BigInteger numOrden) {
        this.numOrden = numOrden;
    }

    public BigInteger getNumfolio() {
        return numfolio;
    }

    public void setNumfolio(BigInteger numfolio) {
        this.numfolio = numfolio;
    }

    public Date getFecha_recepcion() {
        return fecha_recepcion;
    }

    public void setFecha_recepcion(Date fecha_recepcion) {
        this.fecha_recepcion = fecha_recepcion;
    }

    public Date getFecha_aceptacion() {
        return fecha_aceptacion;
    }

    public void setFecha_aceptacion(Date fecha_aceptacion) {
        this.fecha_aceptacion = fecha_aceptacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public short getPeriodo() {
        return periodo;
    }

    public void setPeriodo(short periodo) {
        this.periodo = periodo;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNom_cliente() {
        return nom_cliente;
    }

    public void setNom_cliente(String nom_cliente) {
        this.nom_cliente = nom_cliente;
    }
}
