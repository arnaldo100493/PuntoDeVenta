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
public class Cliente extends Persona implements Serializable {

    public Cliente() {
        super();
    }

    public Cliente(String identificacion, String nombres, String direccion) {
        super(identificacion, nombres, direccion);
    }

    public Cliente(String identificacion, String nombres, String apellidoPaterno, String apellidoMaterno, String direccion, String telefono, String correoElectronico, String sexo, int edad, double talla, double peso) {
        super(identificacion, nombres, apellidoPaterno, apellidoMaterno, direccion, telefono, correoElectronico, sexo, edad, talla, peso);
    }

    public Cliente(String identificacion, String nombres, String apellidoPaterno, String apellidoMaterno, String direccion, String telefono, String correoElectronico, String sexo, int edad, double talla, double peso, Date fechaRegistro) {
        super(identificacion, nombres, apellidoPaterno, apellidoMaterno, direccion, telefono, correoElectronico, sexo, edad, talla, peso, fechaRegistro);
    }

    public String mostrarCliente() {
        return super.mostrarPersona();
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
