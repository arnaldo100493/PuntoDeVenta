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
import com.puntodeventa.modelo.Empleado;

/**
 *
 * @author abarrios
 */
public class ControlEmpleado implements Controlador, Serializable {

    private static List<Empleado> listadoEmpleados;
    private static final String ruta = "../PuntoDeVenta/datos/Empleados.txt/";

    public ControlEmpleado() {
        listadoEmpleados = null;
    }

    public static void setListadoEmpleados(List<Empleado> lista) {
        listadoEmpleados = lista;
    }

    public static List<Empleado> getListadoEmpleados() {
        return ControlEmpleado.listadoEmpleados;
    }

    public void inicializar() {
        try {
            listadoEmpleados = ControlArchivo.leerArchivo("Empleados");
            if (listadoEmpleados == null) {
                listadoEmpleados = new LinkedList<>();
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
            for (int i = 0; i < listadoEmpleados.size(); i++) {
                escribir.println(listadoEmpleados.get(i).toString());
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
            String direccion;
            String telefono;
            String email;
            int sueldo;

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
                // Teléfono
                posicion = linea.indexOf('|');
                aux = linea.substring(0, posicion);
                telefono = aux;
                linea = linea.substring(posicion + 1);
                // Dirección y email
                posicion = linea.indexOf('|');
                aux = linea.substring(0, posicion);
                direccion = aux;
                linea = linea.substring(posicion + 1);
                email = linea;
                // Sueldo
                posicion = linea.indexOf('|');
                aux = linea.substring(0, posicion);
                sueldo = Integer.parseInt(aux);
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
            if (objeto instanceof Empleado) {
                this.inicializar();
                Empleado empleado = (Empleado) objeto;
                if (!this.existe(empleado.getIdentificacion())) {
                    listadoEmpleados.add(empleado);
                    ControlArchivo.guardarArchivo(listadoEmpleados, "Empleados");
                    this.escribir();
                    JOptionPane.showMessageDialog(null, "Empleado registrado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Este empleado ya existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar el empleado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Empleado buscar(String identificacion) {
        this.inicializar();
        String id = identificacion;
        Empleado empleado = null;
        for (Empleado e : listadoEmpleados) {
            if (e.getIdentificacion().equals(id)) {
                empleado = e;
            }
        }
        return empleado;
    }

    @Override
    public boolean existe(String identificacion) {
        this.inicializar();
        boolean estado = false;
        Empleado empleado = this.buscar(identificacion);
        for (int i = 0; i < listadoEmpleados.size(); i++) {
            if (listadoEmpleados.get(i).getIdentificacion().equals(identificacion)) {
                estado = true;
            }
        }
        if (empleado != null) {
            if (empleado.getIdentificacion().equals(identificacion)) {
                estado = true;
            }
        }
        return estado;
    }

    @Override
    public Empleado consultar(String identificacion) {
        Empleado empleado = null;
        try {
            this.inicializar();
            empleado = this.buscar(identificacion);
            for (int i = 0; i < listadoEmpleados.size(); i++) {
                if (listadoEmpleados.get(i).getIdentificacion().equals(identificacion) && empleado != null) {
                    empleado = listadoEmpleados.get(i);
                } else {
                    JOptionPane.showMessageDialog(null, "Este empleado no existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Error al consultar el empleado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return empleado;
    }

    @Override
    public List<Empleado> listar(JTable tabla) {
        List<Empleado> dbistadoEmpleados = new LinkedList<>();
        try {
            this.inicializar();
            ((DefaultTableModel) tabla.getModel()).setNumRows(0);
            for (Empleado empleado : listadoEmpleados) {
                ((DefaultTableModel) tabla.getModel()).addRow(new Object[]{empleado.getIdentificacion(), empleado.getNombres(), empleado.getApellidoPaterno(), empleado.getApellidoMaterno(), empleado.getDireccion(), empleado.getTelefono(), empleado.getCorreoElectronico(), empleado.getSexo(), empleado.getEdad(), empleado.getTalla(), empleado.getPeso(), empleado.getSueldo(), empleado.getFechaRegistro()});
            }
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar el empleado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return dbistadoEmpleados;
    }

    @Override
    public List<Empleado> listar() {
        return listadoEmpleados;
    }

    @Override
    public void modificar(Object objeto) {
        try {
            if (objeto instanceof Empleado) {
                this.inicializar();
                Empleado empleado = (Empleado) objeto;
                for (int i = 0; i < listadoEmpleados.size(); i++) {
                    if (listadoEmpleados.get(i).getIdentificacion().equals(empleado.getIdentificacion())) {
                        listadoEmpleados.remove(i);
                        listadoEmpleados.add(empleado);
                        ControlArchivo.guardarArchivo(listadoEmpleados, "Empleado");
                        this.escribir();
                        JOptionPane.showMessageDialog(null, "Empleado modificado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Este empleado no existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar el empleado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void eliminar(String identificacion) {
        Empleado empleado = null;
        try {
            this.inicializar();
            empleado = this.buscar(identificacion);
            for (int i = 0; i < listadoEmpleados.size(); i++) {
                if (listadoEmpleados.get(i).getIdentificacion().equals(identificacion) && empleado != null) {
                    int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea eliminar este empleado llamado " + empleado.getNombres() + " " + empleado.getApellidoPaterno() + " " + empleado.getApellidoMaterno() + " ? ", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"SI", "NO"}, "SI");
                    if (opcion != -1) {
                        if ((opcion + 1) == 1) {
                            listadoEmpleados.remove(empleado);
                            ControlArchivo.guardarArchivo(listadoEmpleados, "Empleados");
                            this.escribir();
                            JOptionPane.showMessageDialog(null, "Empleado eliminado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Este empleado no existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el empleado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
