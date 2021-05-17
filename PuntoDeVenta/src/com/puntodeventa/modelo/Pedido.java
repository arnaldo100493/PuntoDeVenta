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
public class Pedido implements Serializable {

    private String codigo;
    private String fechaRegistro;

    public Pedido() {
        this.codigo = "";
        this.fechaRegistro = "";
    }

    public Pedido(String codigo) {
        this.codigo = codigo;
        this.fechaRegistro = "";
    }

    public Pedido(String codigo, String fechaRegistro) {
        this.codigo = codigo;
        this.fechaRegistro = fechaRegistro;
    }

    public String mostrarPedido() {
        return "Codigo: " + this.getCodigo() + "Fecha Registro: " + this.getFechaRegistro();
    }

    @Override
    public String toString() {
        return "Pedido{" + "codigo=" + getCodigo() + ", fechaRegistro=" + getFechaRegistro() + '}';
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
