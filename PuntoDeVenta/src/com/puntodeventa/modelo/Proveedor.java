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
public class Proveedor extends Persona implements Serializable {

    private String empresa;
    private String nitEmpresa;

    public Proveedor() {
        super();
        this.empresa = "";
        this.nitEmpresa = "";
    }

    public Proveedor(String empresa, String nitEmpresa) {
        super();
        this.empresa = empresa;
        this.nitEmpresa = nitEmpresa;
    }

    public String mostrarProveedor() {
        return super.mostrarPersona() + "Empresa: " + this.getEmpresa() + "Nit Empresa: " + this.getNitEmpresa();
    }

    @Override
    public String toString() {
        return "Proveedor{" + "empresa=" + getEmpresa() + ", nitEmpresa=" + getNitEmpresa() + '}';
    }

    /**
     * @return the empresa
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the nitEmpresa
     */
    public String getNitEmpresa() {
        return nitEmpresa;
    }

    /**
     * @param nitEmpresa the nitEmpresa to set
     */
    public void setNitEmpresa(String nitEmpresa) {
        this.nitEmpresa = nitEmpresa;
    }

}
