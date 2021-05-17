/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntodeventa.dao;

import com.mysql.cj.jdbc.JdbcConnection;
import com.puntodeventa.jdbc.MySQLConnection;
import com.puntodeventa.modelo.Cliente;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author abarrios
 */
public class DAOCliente implements DAO<Cliente> {

    public DAOCliente() {

    }

    @Override
    public void insertar(Cliente cliente) {
        JdbcConnection connection = MySQLConnection.connect();
        String sql = "insert into cliente(identificacion, nombres, apellido_paterno, apellido_materno, direccion, telefono, correo_electronico, sexo, edad, talla, peso, fecha_registro) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cliente.getIdentificacion());
            statement.setString(2, cliente.getNombres());
            statement.setString(3, cliente.getApellidoPaterno());
            statement.setString(4, cliente.getApellidoMaterno());
            statement.setString(5, cliente.getDireccion());
            statement.setString(6, cliente.getTelefono());
            statement.setString(7, cliente.getCorreoElectronico());
            statement.setString(8, cliente.getSexo());
            statement.setInt(9, cliente.getEdad());
            statement.setDouble(10, cliente.getTalla());
            statement.setDouble(11, cliente.getPeso());
            statement.setDate(12, cliente.getFechaRegistro());

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas != 0) {
                System.out.println("Filas Afectadas: " + filasAfectadas);
            } else {
                System.err.println("No hay filas afectadas");
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    @Override
    public Cliente consultar(String identificacion) {
        Cliente cliente = null;
        String sql = "select * from cliente where identificacion = ?";
        try {
            JdbcConnection connection = MySQLConnection.connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, identificacion);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nombres = resultSet.getString(2);
                String apellidoPaterno = resultSet.getString(3);
                String apellidoMaterno = resultSet.getString(4);
                String direccion = resultSet.getString(5);
                String telefono = resultSet.getString(6);
                String correoElectronico = resultSet.getString(7);
                String sexo = resultSet.getString(8);
                int edad = resultSet.getInt(9);
                double talla = resultSet.getDouble(10);
                double peso = resultSet.getDouble(11);
                Date fechaRegistro = resultSet.getDate(12);

                cliente = new Cliente(identificacion, nombres, apellidoPaterno, apellidoMaterno, direccion, telefono, correoElectronico, sexo, edad, talla, peso, fechaRegistro);

            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        return cliente;
    }

    @Override
    public List<Cliente> listar() {
        List<Cliente> listadoClientes = new LinkedList<>();
        String sql = "select * from cliente";
        try {
            JdbcConnection connection = MySQLConnection.connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String identificacion = resultSet.getString(1);
                String nombres = resultSet.getString(2);
                String apellidoPaterno = resultSet.getString(3);
                String apellidoMaterno = resultSet.getString(4);
                String direccion = resultSet.getString(5);
                String telefono = resultSet.getString(6);
                String correoElectronico = resultSet.getString(7);
                String sexo = resultSet.getString(8);
                int edad = resultSet.getInt(9);
                double talla = resultSet.getDouble(10);
                double peso = resultSet.getDouble(11);
                Date fechaRegistro = resultSet.getDate(12);

                Cliente cliente = new Cliente(identificacion, nombres, apellidoPaterno, apellidoMaterno, direccion, telefono, correoElectronico, sexo, edad, talla, peso, fechaRegistro);
                listadoClientes.add(cliente);

            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        return listadoClientes;

    }

    @Override
    public void actualizar(Cliente cliente) {
        JdbcConnection connection = MySQLConnection.connect();
        String sql = "update cliente set identificacion = ?, nombres = ?, apellido_paterno = ?, apellido_materno = ?, direccion = ?, telefono = ?, correo_electronico = ?, sexo = ?, edad = ?, talla = ?, peso = ? where identificacion = " + cliente.getIdentificacion() + "";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cliente.getIdentificacion());
            statement.setString(2, cliente.getNombres());
            statement.setString(3, cliente.getApellidoPaterno());
            statement.setString(4, cliente.getApellidoMaterno());
            statement.setString(5, cliente.getDireccion());
            statement.setString(6, cliente.getTelefono());
            statement.setString(7, cliente.getCorreoElectronico());
            statement.setString(8, cliente.getSexo());
            statement.setInt(9, cliente.getEdad());
            statement.setDouble(10, cliente.getTalla());
            statement.setDouble(11, cliente.getPeso());

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas != 0) {
                System.out.println("Filas Afectadas: " + filasAfectadas);
            } else {
                System.err.println("No hay filas afectadas");
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }

    }

    @Override
    public void eliminar(String identificacion) {

        JdbcConnection connection = MySQLConnection.connect();
        String sql = "delete from cliente where identificacion = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, identificacion);

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas != 0) {
                System.out.println("Filas Afectadas: " + filasAfectadas);
            } else {
                System.err.println("No hay filas afectadas");
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}
