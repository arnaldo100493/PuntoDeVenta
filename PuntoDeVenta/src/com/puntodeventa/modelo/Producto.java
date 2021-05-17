/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntodeventa.modelo;

import java.io.Serializable;

/**
 *
 * @author abarrios
 */
public class Producto implements Serializable {

    private String codigo;
    private String nombre;
    private String descripcion;
    private int precio;
    private int cantidad;
    private String fechaRegistro;

    public Producto() {
        this.codigo = "";
        this.nombre = "";
        this.descripcion = "";
        this.precio = 0;
        this.cantidad = 0;
        this.fechaRegistro = "";
    }

    public Producto(String codigo, String nombre, String descripcion, int precio, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.fechaRegistro = null;
    }

    public Producto(String codigo, String nombre, String descripcion, int precio, int cantidad, String fechaRegistro) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.fechaRegistro = fechaRegistro;
    }

    public String mostrarProducto() {
        return "Producto{" + "codigo=" + getCodigo() + ", nombre=" + getNombre() + ", descripcion=" + getDescripcion() + ", precio=" + getPrecio() + ", cantidad=" + getCantidad() + ", fechaRegistro=" + getFechaRegistro() + '}';
    }

    @Override
    public String toString() {
        return "Producto{" + "codigo=" + getCodigo() + ", nombre=" + getNombre() + ", descripcion=" + getDescripcion() + ", precio=" + getPrecio() + ", cantidad=" + getCantidad() + ", fechaRegistro=" + getFechaRegistro() + '}';
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the precio
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the fechaRegistro
     */
    public String getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

}
