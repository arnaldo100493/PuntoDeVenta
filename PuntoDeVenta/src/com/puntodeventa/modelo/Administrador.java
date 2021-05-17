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
public class Administrador extends Persona implements Serializable {

    private String usuario;
    private String contrasenia;

    public Administrador() {
        super();
        this.usuario = "";
        this.contrasenia = "";
    }

    public Administrador(String usuario, String contrasenia) {
        super();
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }


    public String mostrarAdministrador() {
        return super.mostrarPersona() + "Usuario: " + this.getUsuario() + "Contraseña: " + this.getContrasenia();
    }

    @Override
    public String toString() {
        return "Administrador{" + "usuario=" + getUsuario() + ", contrasenia=" + getContrasenia() + '}';
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the contrasenia
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * @param contrasenia the contrasenia to set
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    
}
