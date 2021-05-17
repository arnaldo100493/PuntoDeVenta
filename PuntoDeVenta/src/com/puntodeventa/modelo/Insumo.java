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
public class Insumo extends Producto implements Serializable {

    private int cantidadMinima;
    private int cantidadMaxima;

    public Insumo() {
        super();
        this.cantidadMinima = 0;
        this.cantidadMaxima = 0;
    }

    public Insumo(int cantidadMinima, int cantidadMaxima) {
        super();
        this.cantidadMinima = cantidadMinima;
        this.cantidadMaxima = cantidadMaxima;
    }

    public Insumo(String codigo, String nombre, String descripcion, int precio, int cantidad, int cantidadMinima, int cantidadMaxima) {
        super(codigo, nombre, descripcion, precio, cantidad);
        this.cantidadMinima = cantidadMinima;
        this.cantidadMaxima = cantidadMaxima;
    }

    public Insumo(String codigo, String nombre, String descripcion, int precio, int cantidad, String fechaRegistro, int cantidadMinima, int cantidadMaxima) {
        super(codigo, nombre, descripcion, precio, cantidad, fechaRegistro);
        this.cantidadMinima = cantidadMinima;
        this.cantidadMaxima = cantidadMaxima;
    }

    public String mostrarInsumo() {
        return super.mostrarProducto() + "Cantidad Mínima: " + this.getCantidadMinima() + "Cantidad Máxima: " + this.getCantidadMaxima();
    }

    @Override
    public String toString() {
        return "Insumo{" + "cantidadMinima=" + getCantidadMinima() + ", cantidadMaxima=" + getCantidadMaxima() + '}';
    }

    /**
     * @return the cantidadMinima
     */
    public int getCantidadMinima() {
        return cantidadMinima;
    }

    /**
     * @param cantidadMinima the cantidadMinima to set
     */
    public void setCantidadMinima(int cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    /**
     * @return the cantidadMaxima
     */
    public int getCantidadMaxima() {
        return cantidadMaxima;
    }

    /**
     * @param cantidadMaxima the cantidadMaxima to set
     */
    public void setCantidadMaxima(int cantidadMaxima) {
        this.cantidadMaxima = cantidadMaxima;
    }

}
