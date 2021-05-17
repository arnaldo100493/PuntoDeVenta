/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntodeventa.modelo;

/**
 *
 * @author abarrios
 */
public class Vehiculo {

    private String marca;
    private String modelo;
    private String color;
    private int anio;
    private String fecha;
    private double precio;

    public Vehiculo() {
        this.marca = "";
        this.modelo = "";
        this.color = "";
        this.anio = 0;
        this.fecha = "";
        this.precio = 0;
    }

    public Vehiculo(String marca, String modelo, String color, int anio, String fecha, double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.anio = anio;
        this.fecha = fecha;
        this.precio = precio;
    }

    public void acelerar() {

    }

    public void arrancar() {

    }

    public void frenar() {

    }

    public void derrapar() {

    }

    @Override
    public String toString() {
        return "Vehiculo{" + "marca=" + getMarca() + ", modelo=" + getModelo() + ", color=" + getColor() + ", anio=" + getAnio() + ", fecha=" + getFecha() + ", precio=" + getPrecio() + '}';
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the anio
     */
    public int getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    /**
     * @return the fechaConsulta
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fechaConsulta to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

}
