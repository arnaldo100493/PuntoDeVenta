/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntodeventa.control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.puntodeventa.modelo.Insumo;

/**
 *
 * @author abarrios
 */
public class ControlInsumo implements Controlador, Serializable {

    private static List<Insumo> listadoInsumos;
    private static final String ruta = "../PuntoDeVenta/datos/nsumos.txt/";

    public ControlInsumo() {
        listadoInsumos = null;
    }

    public static void setListadoInsumos(List<Insumo> listado) {
        listadoInsumos = listado;
    }

    public static List<Insumo> getListadoInsumos() {
        return listadoInsumos;
    }

    public void inicializar() {
        try {
            listadoInsumos = ControlArchivo.leerArchivo("Insumos");
            if (listadoInsumos == null) {
                listadoInsumos = new LinkedList<>();
            }
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para escribir en el fichero
    @Override
    public void escribir() {
        FileWriter fichero = null;
        PrintWriter escribir = null;
        try {
            fichero = new FileWriter(ruta);
            escribir = new PrintWriter(fichero);
            for (int i = 0; i < listadoInsumos.size(); i++) {
                escribir.println(listadoInsumos.get(i).toString());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Fichero NO Encontrado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } finally {
            try {
                if (fichero != null) {
                    fichero.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    // Método para cargar los datos del fichero a la lista
    @Override
    public void cargarDatos() {
        File fichero = null;
        FileReader leer = null;
        BufferedReader bufLeer;
        try {
            fichero = new File(ruta);
            leer = new FileReader(fichero);
            bufLeer = new BufferedReader(leer);

            String linea;
            String aux;
            int posicion;

            String codigo;
            String nombre;
            String descripcion;
            int precio;
            int cantidad;
            int cantidadMinima;
            int cantidadMaxima;

            while ((linea = bufLeer.readLine()) != null) {
                // Código
                posicion = linea.indexOf('|');
                aux = linea.substring(0, posicion);
                codigo = aux;
                linea = linea.substring(posicion + 1);
                // Nombre
                posicion = linea.indexOf('|');
                aux = linea.substring(0, posicion);
                nombre = aux;
                linea = linea.substring(posicion + 1);
                // Descripcion
                posicion = linea.indexOf('|');
                aux = linea.substring(0, posicion);
                descripcion = aux;
                linea = linea.substring(posicion + 1);
                // Precio
                posicion = linea.indexOf('|');
                aux = linea.substring(0, posicion);
                precio = Integer.parseInt(aux);
                linea = linea.substring(posicion + 1);
                // Cantidad
                posicion = linea.indexOf('|');
                aux = linea.substring(0, posicion);
                cantidad = Integer.parseInt(aux);
                linea = linea.substring(posicion + 1);

                // Cantidad Mínima
                posicion = linea.indexOf('|');
                aux = linea.substring(0, posicion);
                cantidadMinima = Integer.parseInt(aux);
                linea = linea.substring(posicion + 1);

                // Cantidad Máxima
                posicion = linea.indexOf('|');
                aux = linea.substring(0, posicion);
                cantidadMaxima = Integer.parseInt(aux);
                linea = linea.substring(posicion + 1);

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Fichero NO Encontrado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } finally {
            try {
                if (leer != null) {
                    leer.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void registrar(Object objeto) {
        try {
            if (objeto instanceof Insumo) {
                this.inicializar();
                Insumo insumo = (Insumo) objeto;
                if (!this.existe(insumo.getCodigo())) {
                    listadoInsumos.add(insumo);
                    ControlArchivo.guardarArchivo(listadoInsumos, "Insumos");
                    this.escribir();
                    JOptionPane.showMessageDialog(null, "Insumo registrado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Este insumo ya existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar el insumo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Insumo buscar(String codigo) {
        this.inicializar();
        String c = codigo;
        Insumo insumo = null;
        for (Insumo i : listadoInsumos) {
            if (i.getCodigo().equals(c)) {
                insumo = i;
            }
        }
        return insumo;
    }

    @Override
    public boolean existe(String codigo) {
        this.inicializar();
        boolean estado = false;
        Insumo insumo = this.buscar(codigo);
        for (int i = 0; i < listadoInsumos.size(); i++) {
            if (listadoInsumos.get(i).getCodigo().equals(codigo)) {
                estado = true;
            }
        }
        if (insumo != null) {
            if (insumo.getCodigo().equals(codigo)) {
                estado = true;
            }
        }
        return estado;
    }

    @Override
    public Insumo consultar(String codigo) {
        Insumo insumo = null;
        try {
            this.inicializar();
            insumo = this.buscar(codigo);
            for (int i = 0; i < listadoInsumos.size(); i++) {
                if (listadoInsumos.get(i).getCodigo().equals(codigo) && insumo != null) {
                    insumo = listadoInsumos.get(i);
                } else {
                    JOptionPane.showMessageDialog(null, "Este insumo no existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Error al consultar el insumo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return insumo;
    }

    @Override
    public List<Insumo> listar(JTable tabla) {
        List<Insumo> dbListadoInsumos = new LinkedList<>();
        try {
            this.inicializar();
            ((DefaultTableModel) tabla.getModel()).setNumRows(0);
            for (Insumo insumo : listadoInsumos) {
                ((DefaultTableModel) tabla.getModel()).addRow(new Object[]{insumo.getCodigo(), insumo.getNombre(), insumo.getDescripcion(), insumo.getPrecio(), insumo.getCantidad(), insumo.getCantidadMinima(), insumo.getCantidadMaxima()});
            }
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar el insumo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return dbListadoInsumos;
    }

    @Override
    public List<Insumo> listar() {
        return listadoInsumos;
    }

    @Override
    public void modificar(Object objeto) {
        try {
            if (objeto instanceof Insumo) {
                this.inicializar();
                Insumo insumo = (Insumo) objeto;
                for (int i = 0; i < listadoInsumos.size(); i++) {
                    if (listadoInsumos.get(i).getCodigo().equals(insumo.getCodigo())) {
                        listadoInsumos.remove(i);
                        listadoInsumos.add(insumo);
                        ControlArchivo.guardarArchivo(listadoInsumos, "Insumos");
                        this.escribir();
                        JOptionPane.showMessageDialog(null, "Insumo modificado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Este insumo no existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar el insumo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void eliminar(String codigo) {
        Insumo insumo = null;
        try {
            this.inicializar();
            insumo = this.buscar(codigo);
            for (int i = 0; i < listadoInsumos.size(); i++) {
                if (listadoInsumos.get(i).getCodigo().equals(codigo) && insumo != null) {
                    int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea eliminar este insumo llamado " + insumo.getNombre() + " " + " ? ", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"SI", "NO"}, "SI");
                    if (opcion != -1) {
                        if ((opcion + 1) == 1) {
                            listadoInsumos.remove(insumo);
                            ControlArchivo.guardarArchivo(listadoInsumos, "Insumos");
                            this.escribir();
                            JOptionPane.showMessageDialog(null, "Insumo eliminado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Este insumo no existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el insumo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
