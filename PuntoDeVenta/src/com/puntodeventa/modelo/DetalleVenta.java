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
public class DetalleVenta {

    private int cantidad;
    private int total;
    private Producto producto;

    public DetalleVenta() {
        this.cantidad = 0;
        this.total = 0;
        this.producto = null;
    }

    public DetalleVenta(int cantidad, int total) {
        this.cantidad = cantidad;
        this.total = total;
    }

    public DetalleVenta(int cantidad, int total, Producto producto) {
        this.cantidad = cantidad;
        this.total = total;
        this.producto = producto;
    }

    public String mostrarDetalleVenta() {
        return "Cantidad: " + this.getCantidad() + "Total: " + this.getTotal() + "Producto: " + this.getProducto();
    }

    @Override
    public String toString() {
        return "DetalleVenta{" + "cantidad=" + getCantidad() + ", total=" + getTotal() + ", producto=" + getProducto() + '}';
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
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return the producto
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}
