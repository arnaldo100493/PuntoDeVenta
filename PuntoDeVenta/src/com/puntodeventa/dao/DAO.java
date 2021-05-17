/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntodeventa.dao;

import java.util.List;

/**
 *
 * @author abarrios
 */
public interface DAO<T> {
    
    public void insertar(T objeto);

    public T consultar(String id);

    public List<T> listar();

    public void actualizar(T objeto);

    public void eliminar(String id);
    
}
