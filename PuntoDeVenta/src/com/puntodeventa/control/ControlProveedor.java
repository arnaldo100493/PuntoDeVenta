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
import com.puntodeventa.modelo.Proveedor;

/**
 *
 * @author abarrios
 */
public class ControlProveedor implements Controlador, Serializable {

    private static List<Proveedor> listadoProveedores;
    private static final String ruta = "../PuntoDeVenta/datos/datos/Proveedores.txt/";

    public ControlProveedor() {
        listadoProveedores = null;
    }

    public static void setListadoProveedores(List<Proveedor> lista) {
        listadoProveedores = lista;
    }

    public static List<Proveedor> getListadoProveedores() {
        return listadoProveedores;
    }

    public void inicializar() {
        try {
            listadoProveedores = ControlArchivo.leerArchivo("Proveedores");
            if (listadoProveedores == null) {
                listadoProveedores = new LinkedList<>();
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
            for (int i = 0; i < listadoProveedores.size(); i++) {
                escribir.println(listadoProveedores.get(i).toString());
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

            String identificacion;
            String nombre;
            String apellido;
            String sexo;
            String empresa;
            String nitEmpresa;

            while ((linea = bufLeer.readLine()) != null) {
                // Identificación
                posicion = linea.indexOf('|');
                aux = linea.substring(0, posicion);
                identificacion = aux;
                linea = linea.substring(posicion + 1);
                // Nombre
                posicion = linea.indexOf('|');
                aux = linea.substring(0, posicion);
                nombre = aux;
                linea = linea.substring(posicion + 1);
                // Apellido
                posicion = linea.indexOf('|');
                aux = linea.substring(0, posicion);
                apellido = aux;
                linea = linea.substring(posicion + 1);
                // Sexo
                posicion = linea.indexOf('|');
                aux = linea.substring(0, posicion);
                sexo = aux;
                linea = linea.substring(posicion + 1);
                // Empresa
                posicion = linea.indexOf('|');
                aux = linea.substring(0, posicion);
                empresa = aux;
                linea = linea.substring(posicion + 1);
                // Nit Empresa
                posicion = linea.indexOf('|');
                aux = linea.substring(0, posicion);
                nitEmpresa = aux;
                linea = linea.substring(posicion + 1);

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Fichero NO Encontrado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (leer != null) {
                    leer.close();
                }
            } catch (Exception ex) {
            }
        }
    }

    @Override
    public void registrar(Object objeto) {
        try {
            if (objeto instanceof Proveedor) {
                this.inicializar();
                Proveedor proveedor = (Proveedor) objeto;
                if (!this.existe(proveedor.getIdentificacion())) {
                    listadoProveedores.add(proveedor);
                    ControlArchivo.guardarArchivo(listadoProveedores, "Proveedores");
                    this.escribir();
                    JOptionPane.showMessageDialog(null, "Proveedor registrado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Este proveedor ya existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar el proveedor: ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Proveedor buscar(String identificacion) {
        this.inicializar();
        String id = identificacion;
        Proveedor proveedor = null;
        for (Proveedor p : listadoProveedores) {
            if (p.getIdentificacion().equals(id)) {
                proveedor = p;
            }
        }
        return proveedor;
    }

    @Override
    public boolean existe(String identificacion) {
        this.inicializar();
        boolean estado = false;
        Proveedor proveedor = this.buscar(identificacion);
        for (int i = 0; i < listadoProveedores.size(); i++) {
            if (listadoProveedores.get(i).getIdentificacion().equals(identificacion)) {
                estado = true;
            }
        }
        if (proveedor != null) {
            if (proveedor.getIdentificacion().equals(identificacion)) {
                estado = true;
            }
        }
        return estado;
    }

    @Override
    public Proveedor consultar(String identificacion) {
        Proveedor proveedor = null;
        try {
            this.inicializar();
            proveedor = this.buscar(identificacion);
            for (int i = 0; i < listadoProveedores.size(); i++) {
                if (listadoProveedores.get(i).getIdentificacion().equals(identificacion) && proveedor != null) {
                    proveedor = listadoProveedores.get(i);
                } else {
                    JOptionPane.showMessageDialog(null, "Este proveedor no existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Error al consultar el proveedor: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return proveedor;
    }

    @Override
    public List<Proveedor> listar(JTable tabla) {
        List<Proveedor> dbListadoProveedores = new LinkedList<>();
        try {
            this.inicializar();
            ((DefaultTableModel) tabla.getModel()).setNumRows(0);
            for (Proveedor proveedor : listadoProveedores) {
                ((DefaultTableModel) tabla.getModel()).addRow(new Object[]{proveedor.getIdentificacion(), proveedor.getNombres(), proveedor.getApellidoPaterno(), proveedor.getApellidoMaterno(), proveedor.getDireccion(), proveedor.getTelefono(), proveedor.getCorreoElectronico(), proveedor.getSexo(), proveedor.getEdad(), proveedor.getTalla(), proveedor.getPeso(), proveedor.getEmpresa(), proveedor.getNitEmpresa(), proveedor.getFechaRegistro()});
            }
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar el proveedor: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return dbListadoProveedores;
    }

    @Override
    public List<Proveedor> listar() {
        return listadoProveedores;
    }

    @Override
    public void modificar(Object objeto) {
        try {
            if (objeto instanceof Proveedor) {
                this.inicializar();
                Proveedor proveedor = (Proveedor) objeto;
                for (int i = 0; i < listadoProveedores.size(); i++) {
                    if (listadoProveedores.get(i).getIdentificacion().equals(proveedor.getIdentificacion())) {
                        listadoProveedores.remove(i);
                        listadoProveedores.add(proveedor);
                        ControlArchivo.guardarArchivo(listadoProveedores, "Proveedores");
                        this.escribir();
                        JOptionPane.showMessageDialog(null, "Proveedor modificado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Este proveedor no existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar el proveedor: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void eliminar(String identificacion) {
        Proveedor proveedor = null;
        try {
            this.inicializar();
            proveedor = this.buscar(identificacion);
            for (int i = 0; i < listadoProveedores.size(); i++) {
                if (listadoProveedores.get(i).getIdentificacion().equals(identificacion) && proveedor != null) {
                    int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea eliminar este proveedor llamado " + proveedor.getNombres() + " " + proveedor.getApellidoPaterno() + " " + proveedor.getApellidoMaterno() +   " ? ", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"SI", "NO"}, "SI");
                    if (opcion != -1) {
                        if ((opcion + 1) == 1) {
                            listadoProveedores.remove(proveedor);
                            ControlArchivo.guardarArchivo(listadoProveedores, "Proveedores");
                            this.escribir();
                            JOptionPane.showMessageDialog(null, "Proveedor eliminado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Este proveedor no existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el proveedor: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
