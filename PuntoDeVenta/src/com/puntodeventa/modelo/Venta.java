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
public class Venta {

    private String codigo;
    private int montoPago;
    private int montoTotal;
    private String fechaRegistro;
    private Producto producto;
    private DetalleVenta detalleVenta;
    private Cliente cliente;

    public Venta() {
        this.codigo = "";
        this.montoPago = 0;
        this.montoTotal = 0;
        this.fechaRegistro = "";
        this.producto = null;
        this.detalleVenta = null;
        this.cliente = null;
    }

    public Venta(String codigo, int montoPago, int montoTotal) {
        this.codigo = codigo;
        this.montoPago = montoPago;
        this.montoTotal = montoTotal;
        this.fechaRegistro = "";
        this.producto = null;
        this.detalleVenta = null;
        this.cliente = null;
    }

    public Venta(String codigo, int montoPago, int montoTotal, String fechaRegistro) {
        this.codigo = codigo;
        this.montoPago = montoPago;
        this.montoTotal = montoTotal;
        this.fechaRegistro = fechaRegistro;
        this.producto = null;
        this.detalleVenta = null;
        this.cliente = null;
    }

    public Venta(String codigo, int montoPago, int montoTotal, Producto producto) {
        this.codigo = codigo;
        this.montoPago = montoPago;
        this.montoTotal = montoTotal;
        this.fechaRegistro = "";
        this.producto = producto;
        this.detalleVenta = null;
        this.cliente = null;
    }
    
    public Venta(String codigo, int montoPago, int montoTotal, String fechaRegistro, Producto producto) {
        this.codigo = codigo;
        this.montoPago = montoPago;
        this.montoTotal = montoTotal;
        this.fechaRegistro = fechaRegistro;
        this.producto = producto;
        this.detalleVenta = null;
        this.cliente = null;
    }

    public Venta(String codigo, int montoPago, int montoTotal, String fechaRegistro, Producto producto, DetalleVenta detalleVenta, Cliente cliente) {
        this.codigo = codigo;
        this.montoPago = montoPago;
        this.montoTotal = montoTotal;
        this.fechaRegistro = fechaRegistro;
        this.producto = producto;
        this.detalleVenta = detalleVenta;
        this.cliente = cliente;
    }

    public String mostrarVenta() {
        return "Venta{" + "codigo=" + getCodigo() + ", montoPago=" + getMontoPago() + ", montoTotal=" + getMontoTotal() + ", fechaRegistro=" + getFechaRegistro() + ", producto=" + getProducto() + ", detalleVenta=" + getDetalleVenta() + ", cliente=" + getCliente() + '}';
    }

    @Override
    public String toString() {
        return "Venta{" + "codigo=" + getCodigo() + ", montoPago=" + getMontoPago() + ", montoTotal=" + getMontoTotal() + ", fechaRegistro=" + getFechaRegistro() + ", producto=" + getProducto() + ", detalleVenta=" + getDetalleVenta() + ", cliente=" + getCliente() + '}';
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
     * @return the montoPago
     */
    public int getMontoPago() {
        return montoPago;
    }

    /**
     * @param montoPago the montoPago to set
     */
    public void setMontoPago(int montoPago) {
        this.montoPago = montoPago;
    }

    /**
     * @return the montoTotal
     */
    public int getMontoTotal() {
        return montoTotal;
    }

    /**
     * @param montoTotal the montoTotal to set
     */
    public void setMontoTotal(int montoTotal) {
        this.montoTotal = montoTotal;
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

    /**
     * @return the detalleVenta
     */
    public DetalleVenta getDetalleVenta() {
        return detalleVenta;
    }

    /**
     * @param detalleVenta the detalleVenta to set
     */
    public void setDetalleVenta(DetalleVenta detalleVenta) {
        this.detalleVenta = detalleVenta;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
