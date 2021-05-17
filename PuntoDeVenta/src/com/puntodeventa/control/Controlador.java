/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntodeventa.control;

import java.io.Serializable;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author abarrios
 */
public interface Controlador<T> extends Serializable {

    public void escribir();

    public void cargarDatos();

    public void registrar(T objeto);

    public T buscar(String identificacion);

    public boolean existe(String identificacion);

    public T consultar(String identificacion);

    public List<T> listar(JTable tabla);

    public List<T> listar();

    public void modificar(T objeto);

    public void eliminar(String identificacion);
}
