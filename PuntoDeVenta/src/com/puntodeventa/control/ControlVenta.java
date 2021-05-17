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
import com.puntodeventa.modelo.Venta;

/**
 *
 * @author abarrios
 */
public class ControlVenta implements Controlador, Serializable {

    private static List<Venta> listadoVentas;
    private static final String ruta = "../PuntoDeVenta/datos/Ventas.txt/";

    public ControlVenta() {
        listadoVentas = null;
    }

    public static void setListadoVentas(List<Venta> listado) {
        listadoVentas = listado;
    }

    public static List<Venta> getListadoVentas() {
        return listadoVentas;
    }

    public void inicializar() {
        try {
            listadoVentas = ControlArchivo.leerArchivo("Ventas");
            if (listadoVentas == null) {
                listadoVentas = new LinkedList<>();
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
            for (int i = 0; i < listadoVentas.size(); i++) {
                escribir.println(listadoVentas.get(i).toString());
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
            if (objeto instanceof Venta) {
                this.inicializar();
                Venta venta = (Venta) objeto;
                if (!this.existe(venta.getCodigo())) {
                    listadoVentas.add(venta);
                    ControlArchivo.guardarArchivo(ControlVenta.listadoVentas, "Ventas");
                    this.escribir();
                    JOptionPane.showMessageDialog(null, "Venta registrada con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Esta venta ya existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar la venta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Venta buscar(String codigo) {
        this.inicializar();
        String c = codigo;
        Venta venta = null;
        for (Venta v : listadoVentas) {
            if (v.getCodigo().equals(c)) {
                venta = v;
            }
        }
        return venta;
    }

    @Override
    public boolean existe(String codigo) {
        this.inicializar();
        boolean estado = false;
        Venta venta = this.buscar(codigo);
        for (int i = 0; i < listadoVentas.size(); i++) {
            if (listadoVentas.get(i).getCodigo().equals(codigo)) {
                estado = true;
            }
        }
        if (venta != null) {
            if (venta.getCodigo().equals(codigo)) {
                estado = true;
            }
        }
        return estado;
    }

    @Override
    public Venta consultar(String codigo) {
        Venta venta = null;
        try {
            this.inicializar();
            venta = this.buscar(codigo);
            for (int i = 0; i < listadoVentas.size(); i++) {
                if (listadoVentas.get(i).getCodigo().equals(codigo) && venta != null) {
                    venta = listadoVentas.get(i);
                } else {
                    JOptionPane.showMessageDialog(null, "Esta venta no existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Error al consultar la venta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return venta;
    }

    @Override
    public List<Venta> listar(JTable tabla) {
        List<Venta> dbListadoVentas = new LinkedList<>();
        try {
            this.inicializar();
            ((DefaultTableModel) tabla.getModel()).setNumRows(0);
            for (Venta venta : listadoVentas) {
                ((DefaultTableModel) tabla.getModel()).addRow(new Object[]{venta.getCodigo(), venta.getMontoPago(), venta.getMontoTotal(), venta.getProducto().getNombre(), venta.getDetalleVenta().getTotal(), venta.getCliente().getNombres() + " " + venta.getCliente().getApellidoPaterno() + " " + venta.getCliente().getApellidoMaterno(), venta.getFechaRegistro()});
            }
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar la venta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return dbListadoVentas;
    }

    @Override
    public List<Venta> listar() {
        return listadoVentas;
    }

    @Override
    public void modificar(Object objeto) {
        try {
            if (objeto instanceof Venta) {
                this.inicializar();
                Venta venta = (Venta) objeto;
                for (int i = 0; i < listadoVentas.size(); i++) {
                    if (listadoVentas.get(i).getCodigo().equals(venta.getCodigo())) {
                        listadoVentas.remove(i);
                        listadoVentas.add(venta);
                        ControlArchivo.guardarArchivo(listadoVentas, "Ventas");
                        this.escribir();
                        JOptionPane.showMessageDialog(null, "Venta modificada con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Esta producta no existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void eliminar(String codigo) {
        Venta venta = null;
        try {
            this.inicializar();
            venta = this.buscar(codigo);
            for (int i = 0; i < listadoVentas.size(); i++) {
                if (listadoVentas.get(i).getCodigo().equals(codigo) && venta != null) {
                    int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea eliminar este producta llamada " + venta.getCodigo() + " " + " ? ", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"SI", "NO"}, "SI");
                    if (opcion != -1) {
                        if ((opcion + 1) == 1) {
                            listadoVentas.remove(venta);
                            ControlArchivo.guardarArchivo(listadoVentas, "Ventas");
                            this.escribir();
                            JOptionPane.showMessageDialog(null, "Venta eliminada con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Esta venta no existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la venta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
