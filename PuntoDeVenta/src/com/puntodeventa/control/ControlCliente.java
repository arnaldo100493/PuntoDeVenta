/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntodeventa.control;

import com.puntodeventa.dao.DAOCliente;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.puntodeventa.modelo.Cliente;
import com.puntodeventa.utilidades.SwingMessages;

/**
 *
 * @author abarrios
 */
public class ControlCliente implements Controlador, Serializable {

    private static List<Cliente> listadoClientes;
    private static final String ruta = "../PuntoDeVenta/datos/Clientes.txt/";
    private DAOCliente daoCliente;

    public ControlCliente() {
        listadoClientes = null;
        daoCliente = null;
    }

    public static void setListadoClientes(List<Cliente> listado) {
        listadoClientes = listado;
    }

    public static List<Cliente> getListadoClientes() {
        return listadoClientes;
    }

    private void inicializar() {
        this.daoCliente = new DAOCliente();
        try {
            listadoClientes = ControlArchivo.leerArchivo("Clientes");
            if (listadoClientes == null) {
                listadoClientes = new LinkedList<>();
            }
        } catch (IOException | ClassNotFoundException ex) {
            SwingMessages.mostrarDialogoMensajeError("Error al leer el archivo: " + ex.getMessage());
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
            for (int i = 0; i < listadoClientes.size(); i++) {
                escribir.println(listadoClientes.get(i).toString());
            }
        } catch (Exception ex) {
            SwingMessages.mostrarDialogoMensajeError("Fichero NO Encontrado: " + ex.getMessage());
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
            String apellidoPaterno;
            String apellidoMaterno;
            String direccion;
            String telefono;
            String correoElectronico;
            String sexo;
            String edad;
            String talla;
            String peso;
            String fechaRegistro;

            while ((linea = bufLeer.readLine()) != null) {
                //Identificación
                posicion = linea.indexOf(",");
                aux = linea.substring(0, posicion);
                identificacion = aux;
                linea = linea.substring(posicion + 1);
                //Nombres
                posicion = linea.indexOf(',');
                aux = linea.substring(0, posicion);
                nombre = aux;
                linea = linea.substring(posicion + 1);
                //Apellido Paterno
                posicion = linea.indexOf(',');
                aux = linea.substring(0, posicion);
                apellidoPaterno = aux;
                linea = linea.substring(posicion + 1);
                //Apellido Materno
                posicion = linea.indexOf(',');
                aux = linea.substring(0, posicion);
                apellidoMaterno = aux;
                linea = linea.substring(posicion + 1);
                //Dirección
                posicion = linea.indexOf(',');
                aux = linea.substring(0, posicion);
                direccion = aux;
                linea = linea.substring(posicion + 1);
                //Teléfono
                posicion = linea.indexOf(',');
                aux = linea.substring(0, posicion);
                telefono = aux;
                linea = linea.substring(posicion + 1);
                //Correo Electrónico
                posicion = linea.indexOf(',');
                aux = linea.substring(0, posicion);
                correoElectronico = aux;
                linea = linea.substring(posicion + 1);
                //Sexo
                posicion = linea.indexOf(',');
                aux = linea.substring(0, posicion);
                sexo = aux;
                linea = linea.substring(posicion + 1);
                //Edad
                posicion = linea.indexOf(',');
                aux = linea.substring(0, posicion);
                edad = aux;
                linea = linea.substring(posicion + 1);
                //Talla
                posicion = linea.indexOf(',');
                aux = linea.substring(0, posicion);
                talla = aux;
                linea = linea.substring(posicion + 1);
                //Peso
                posicion = linea.indexOf(',');
                aux = linea.substring(0, posicion);
                peso = aux;
                linea = linea.substring(posicion + 1);
                //Fecha de Registro
                posicion = linea.lastIndexOf('}');
                aux = linea.substring(0, posicion);
                fechaRegistro = aux;
                linea = linea.substring(posicion + 1);

                fechaRegistro = linea;

            }

        } catch (Exception ex) {
            SwingMessages.mostrarDialogoMensajeError("Fichero NO Encontrado: " + ex.getMessage());
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
            if (objeto instanceof Cliente) {
                this.inicializar();
                Cliente cliente = (Cliente) objeto;
                if (!this.existe(cliente.getIdentificacion())) {
                    listadoClientes.add(cliente);
                    ControlArchivo.guardarArchivo(listadoClientes, "Clientes");
                    this.escribir();
                    this.daoCliente.insertar(cliente);
                    SwingMessages.mostrarDialogoMensajeInformacion("Cliente registrado con éxito");
                } else {
                    SwingMessages.mostrarDialogoMensajeAdvertencia("Este cliente ya existe");
                }
            }
        } catch (IOException ex) {
            SwingMessages.mostrarDialogoMensajeError("Error al registrar el cliente: " + ex.getMessage());
        }
    }

    @Override
    public Cliente buscar(String identificacion) {
        this.inicializar();
        String id = identificacion;
        Cliente cliente = null;
        for (Cliente c : listadoClientes) {
            if (c.getIdentificacion().equals(id)) {
                cliente = c;
            }
        }
        return cliente;
    }

    @Override
    public boolean existe(String identificacion) {
        this.inicializar();
        boolean estado = false;
        Cliente cliente = this.buscar(identificacion);
        Cliente bdCliente = this.daoCliente.consultar(identificacion);
        for (int i = 0; i < listadoClientes.size(); i++) {
            if (listadoClientes.get(i).getIdentificacion().equals(identificacion)) {
                estado = true;
            }
        }
        if (cliente != null) {
            if (cliente.getIdentificacion().equals(identificacion)) {
                estado = true;
            }
        }
        if (bdCliente != null) {
            if (bdCliente.getIdentificacion().equals(identificacion)) {
                estado = true;
            }
        }
        return estado;
    }

    @Override
    public Cliente consultar(String identificacion) {
        Cliente cliente = null;
        Cliente dbCliente = null;
        try {
            this.inicializar();
            cliente = this.buscar(identificacion);
            dbCliente = this.daoCliente.consultar(identificacion);
            for (int i = 0; i < listadoClientes.size(); i++) {
                if (listadoClientes.get(i).getIdentificacion().equals(identificacion) && cliente != null && dbCliente != null) {
                    cliente = listadoClientes.get(i);
                } else {
                    SwingMessages.mostrarDialogoMensajeAdvertencia("Este cliente no existe");
                }
            }
        } catch (NullPointerException ex) {
            SwingMessages.mostrarDialogoMensajeError("Error al consultar el cliente: " + ex.getMessage());
        }
        return dbCliente;
    }

    @Override
    public List<Cliente> listar(JTable tabla) {
        List<Cliente> dbListadoClientes = null;
        try {
            this.inicializar();
            dbListadoClientes = this.daoCliente.listar();
            ((DefaultTableModel) tabla.getModel()).setNumRows(0);
            for (Cliente cliente : listadoClientes) {
                ((DefaultTableModel) tabla.getModel()).addRow(new Object[]{cliente.getIdentificacion(), cliente.getNombres(), cliente.getApellidoPaterno(), cliente.getApellidoMaterno(), cliente.getDireccion(), cliente.getTelefono(), cliente.getCorreoElectronico(), cliente.getSexo(), cliente.getEdad(), cliente.getTalla(), cliente.getPeso(), cliente.getFechaRegistro()});
            }
        } catch (NullPointerException ex) {
            SwingMessages.mostrarDialogoMensajeError("Error al listar el cliente: " + ex.getMessage());
        }
        return dbListadoClientes;
    }

    @Override
    public List<Cliente> listar() {
        return listadoClientes;
    }

    @Override
    public void modificar(Object objeto) {
        try {
            if (objeto instanceof Cliente) {
                this.inicializar();
                Cliente cliente = (Cliente) objeto;
                for (int i = 0; i < listadoClientes.size(); i++) {
                    if (listadoClientes.get(i).getIdentificacion().equals(cliente.getIdentificacion())) {
                        listadoClientes.remove(i);
                        listadoClientes.add(cliente);
                        ControlArchivo.guardarArchivo(listadoClientes, "Clientes");
                        this.escribir();
                        this.daoCliente.actualizar(cliente);
                        SwingMessages.mostrarDialogoMensajeInformacion("Cliente modificado con éxito");
                    } else {
                        SwingMessages.mostrarDialogoMensajeAdvertencia("Este cliente no existe");
                    }
                }
            }
        } catch (IOException ex) {
            SwingMessages.mostrarDialogoMensajeError("Error al modificar el cliente: " + ex.getMessage());
        }
    }

    @Override
    public void eliminar(String identificacion) {
        Cliente cliente = null;
        try {
            this.inicializar();
            cliente = this.buscar(identificacion);
            for (int i = 0; i < listadoClientes.size(); i++) {
                if (listadoClientes.get(i).getIdentificacion().equals(identificacion) && cliente != null) {
                    int opcion = SwingMessages.mostrarDialogoMensajeOpcion("¿Está seguro que desea eliminar este cliente llamado " + cliente.getNombres() + " " + cliente.getApellidoPaterno() + "" + cliente.getApellidoMaterno() + " ?");
                    if (opcion != -1) {
                        if ((opcion + 1) == 1) {
                            listadoClientes.remove(cliente);
                            ControlArchivo.guardarArchivo(listadoClientes, "Clientes");
                            this.escribir();
                            this.daoCliente.eliminar(identificacion);
                            SwingMessages.mostrarDialogoMensajeInformacion("Cliente eliminado con éxito");
                        }
                    }
                } else {
                    SwingMessages.mostrarDialogoMensajeAdvertencia("Este cliente no existe");
                }
            }
        } catch (IOException ex) {
            SwingMessages.mostrarDialogoMensajeError("Error al eliminar el cliente: " + ex.getMessage());
        }
    }
}
