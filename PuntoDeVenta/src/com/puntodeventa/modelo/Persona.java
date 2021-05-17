/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntodeventa.modelo;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author abarrios
 */
public abstract class Persona implements Serializable {

    //Definimos nuestros atributos
    protected String identificacion;
    protected String nombres;
    protected String apellidoPaterno;
    protected String apellidoMaterno;
    protected String direccion;
    protected String telefono;
    protected String correoElectronico;
    protected String sexo;
    protected int edad;
    protected double talla;
    protected double peso;
    protected Date fechaRegistro;

    //Definimos nuestros constructores
    public Persona() {
        this.identificacion = "";
        this.nombres = "";
        this.apellidoPaterno = "";
        this.apellidoMaterno = "";
        this.direccion = "";
        this.telefono = "";
        this.correoElectronico = "";
        this.sexo = "";
        this.edad = 0;
        this.talla = 0.0;
        this.peso = 0.0;
        this.fechaRegistro = null;
    }

    public Persona(String identificacion, String nombres, String direccion) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidoPaterno = "";
        this.apellidoMaterno = "";
        this.direccion = direccion;
        this.telefono = "";
        this.correoElectronico = "";
        this.sexo = "";
        this.edad = 0;
        this.talla = 0;
        this.peso = 0;
        this.fechaRegistro = null;
    }

    public Persona(String identificacion, String nombres, String apellidoPaterno, String apellidoMaterno, String direccion, String telefono, String correoElectronico, String sexo, int edad, double talla, double peso) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.sexo = sexo;
        this.edad = edad;
        this.talla = talla;
        this.peso = peso;
        this.fechaRegistro = null;
    }

    public Persona(String identificacion, String nombres, String apellidoPaterno, String apellidoMaterno, String direccion, String telefono, String correoElectronico, String sexo, int edad, double talla, double peso, Date fechaRegistro) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.sexo = sexo;
        this.edad = edad;
        this.talla = talla;
        this.peso = peso;
        this.fechaRegistro = fechaRegistro;
    }

    //Definimos nuestros métodos
    public void caminar() {

    }

    public void correr() {

    }

    public void dormir() {

    }

    public void comer() {

    }

    public String mostrarPersona() {
        return "Persona{" + "identificacion=" + getIdentificacion() + ", nombres=" + getNombres() + ", apellidoPaterno=" + getApellidoPaterno() + ", apellidoMaterno=" + getApellidoMaterno() + ", direccion=" + getDireccion() + ", telefono=" + getTelefono() + ", correoElectronico=" + getCorreoElectronico() + ", sexo=" + getSexo() + ", edad=" + getEdad() + ", talla=" + getTalla() + ", peso=" + getPeso() + ", fechaRegistro=" + getFechaRegistro() + '}';
    }

    @Override
    public String toString() {
        return "Persona{" + "identificacion=" + getIdentificacion() + ", nombres=" + getNombres() + ", apellidoPaterno=" + getApellidoPaterno() + ", apellidoMaterno=" + getApellidoMaterno() + ", direccion=" + getDireccion() + ", telefono=" + getTelefono() + ", correoElectronico=" + getCorreoElectronico() + ", sexo=" + getSexo() + ", edad=" + getEdad() + ", talla=" + getTalla() + ", peso=" + getPeso() + ", fechaRegistro=" + getFechaRegistro() + '}';
    }

    /**
     * @return the identificacion
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * @param identificacion the identificacion to set
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the apellidoPaterno
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * @param apellidoPaterno the apellidoPaterno to set
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * @return the apellidoMaterno
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * @param apellidoMaterno the apellidoMaterno to set
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the correoElectronico
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * @param correoElectronico the correoElectronico to set
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * @return the talla
     */
    public double getTalla() {
        return talla;
    }

    /**
     * @param talla the talla to set
     */
    public void setTalla(double talla) {
        this.talla = talla;
    }

    /**
     * @return the peso
     */
    public double getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * @return the fechaRegistro
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

}
