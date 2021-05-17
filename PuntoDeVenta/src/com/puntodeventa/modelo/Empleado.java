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
public class Empleado extends Persona implements Serializable {

    private int sueldo;

    public Empleado() {
        super();
        this.sueldo = 0;
    }

    public Empleado(int sueldo) {
        super();
        this.sueldo = sueldo;
    }

    public String mostrarEmpleado() {
        return super.mostrarPersona() + "Sueldo: " + this.getSueldo();
    }

    @Override
    public String toString() {
        return super.toString() + this.getSueldo();
    }

    /**
     * @return the sueldo
     */
    public int getSueldo() {
        return sueldo;
    }

    /**
     * @param sueldo the sueldo to set
     */
    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

}
