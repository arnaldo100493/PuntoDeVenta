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
import com.puntodeventa.modelo.Producto;

/**
 *
 * @author abarrios
 */
public class ControlProducto implements Controlador, Serializable {

    private static List<Producto> listadoProductos;
    private static final String ruta = "../PuntoDeVenta/datos/Productos.txt/";

    public ControlProducto() {
        listadoProductos = null;
    }

    public static void setListadoProductos(List<Producto> listado) {
        listadoProductos = listado;
    }

    public static List<Producto> getListadoProductos() {
        return listadoProductos;
    }

    public void inicializar() {
        try {
            listadoProductos = ControlArchivo.leerArchivo("Productos");
            if (listadoProductos == null) {
                listadoProductos = new LinkedList<>();
            }
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void escribir() {
        FileWriter fichero = null;
        PrintWriter escribir = null;
        try {
            fichero = new FileWriter(ruta);
            escribir = new PrintWriter(fichero);
            for (int i = 0; i < listadoProductos.size(); i++) {
                escribir.println(listadoProductos.get(i).toString());
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
            if (objeto instanceof Producto) {
                this.inicializar();
                Producto producto = (Producto) objeto;
                if (!this.existe(producto.getCodigo())) {
                    listadoProductos.add(producto);
                    ControlArchivo.guardarArchivo(ControlProducto.listadoProductos, "Productos");
                    this.escribir();
                    JOptionPane.showMessageDialog(null, "Producto registrado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Este producto ya existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Producto buscar(String codigo) {
        this.inicializar();
        String c = codigo;
        Producto producto = null;
        for (Producto p : listadoProductos) {
            if (p.getCodigo().equals(c)) {
                producto = p;
            }
        }
        return producto;
    }

    @Override
    public boolean existe(String codigo) {
        this.inicializar();
        boolean estado = false;
        Producto producto = this.buscar(codigo);
        for (int i = 0; i < listadoProductos.size(); i++) {
            if (listadoProductos.get(i).getCodigo().equals(codigo)) {
                estado = true;
            }
        }
        if (producto != null) {
            if (producto.getCodigo().equals(codigo)) {
                estado = true;
            }
        }
        return estado;
    }

    @Override
    public Producto consultar(String codigo) {
        Producto producto = null;
        try {
            this.inicializar();
            producto = this.buscar(codigo);
            for (int i = 0; i < listadoProductos.size(); i++) {
                if (listadoProductos.get(i).getCodigo().equals(codigo) && producto != null) {
                    producto = listadoProductos.get(i);
                } else {
                    JOptionPane.showMessageDialog(null, "Este producto no existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Error al consultar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return producto;
    }

    @Override
    public List<Producto> listar(JTable tabla) {
        List<Producto> dbListadoProductos = new LinkedList<>();
        try {
            this.inicializar();
            ((DefaultTableModel) tabla.getModel()).setNumRows(0);
            for (Producto producto : listadoProductos) {
                ((DefaultTableModel) tabla.getModel()).addRow(new Object[]{producto.getCodigo(), producto.getNombre(), producto.getDescripcion(), producto.getPrecio(), producto.getCantidad(), producto.getFechaRegistro()});
            }
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return dbListadoProductos;
    }

    @Override
    public List<Producto> listar() {
        return listadoProductos;
    }

    @Override
    public void modificar(Object objeto) {
        try {
            if (objeto instanceof Producto) {
                this.inicializar();
                Producto producto = (Producto) objeto;
                for (int i = 0; i < listadoProductos.size(); i++) {
                    if (listadoProductos.get(i).getCodigo().equals(producto.getCodigo())) {
                        listadoProductos.remove(i);
                        listadoProductos.add(producto);
                        ControlArchivo.guardarArchivo(listadoProductos, "Productos");
                        this.escribir();
                        JOptionPane.showMessageDialog(null, "Producto modificado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Este producto no existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void eliminar(String codigo) {
        Producto producto = null;
        try {
            this.inicializar();
            producto = this.buscar(codigo);
            for (int i = 0; i < listadoProductos.size(); i++) {
                if (listadoProductos.get(i).getCodigo().equals(codigo) && producto != null) {
                    int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea eliminar este producto llamado " + producto.getNombre() + " " + " ? ", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"SI", "NO"}, "SI");
                    if (opcion != -1) {
                        if ((opcion + 1) == 1) {
                            listadoProductos.remove(producto);
                            ControlArchivo.guardarArchivo(listadoProductos, "Productos");
                            this.escribir();
                            JOptionPane.showMessageDialog(null, "Producto eliminado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Este producto no existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
