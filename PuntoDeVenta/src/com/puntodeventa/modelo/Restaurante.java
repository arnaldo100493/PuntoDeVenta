package com.puntodeventa.modelo;

import java.io.Serializable;

public class Restaurante implements Serializable {

    private String correoElectronico;
    private String telefono;

    public Restaurante() {
        this.correoElectronico = "";
        this.telefono = "";
    }

    public Restaurante(String correoElectronico, String telefono) {
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
    }

    public Restaurante(Restaurante restaurante) {
        this.correoElectronico = restaurante.getCorreoElectronico();
        this.telefono = restaurante.getTelefono();
    }

    public String mostrarRestaurante() {
        return "Correo Electrónico: " + this.correoElectronico + "Teléfono: " + this.telefono;
    }

    @Override
    public String toString() {
        return this.correoElectronico + "|" + this.telefono;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getCorreoElectronico() {
        return this.correoElectronico;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono() {
        return this.telefono;
    }

}
