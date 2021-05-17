/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntodeventa.pruebas;

import com.mysql.cj.jdbc.JdbcConnection;
import com.puntodeventa.jdbc.MySQLConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author abarrios
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        actualizar();

    }

    public static void insertar() {
        JdbcConnection connection = MySQLConnection.connect();
        String sql = "insert into cliente(identificacion, nombres, apellido_paterno, apellido_materno, direccion, telefono, correo_electronico, sexo, edad, talla, peso, fecha_registro) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "8853492");
            statement.setString(2, "Brainer Arnold");
            statement.setString(3, "Barrios");
            statement.setString(4, "Mena");
            statement.setString(5, "Quebec");
            statement.setString(6, "343434");
            statement.setString(7, "brabame@hotmail.com");
            statement.setString(8, "Masculino");
            statement.setInt(9, 28);
            statement.setDouble(10, 1.60);
            statement.setDouble(11, 80);
            statement.setDate(12, new Date(2021, 5, 2));

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

    public static void actualizar() {
        JdbcConnection connection = MySQLConnection.connect();
        String sql = "update cliente set identificacion = ?, nombres = ?, apellido_paterno = ?, apellido_materno = ?, direccion = ?, telefono = ?, correo_electronico = ?, sexo = ?, edad = ?, talla = ?, peso = ? where identificacion = " +"323232"+"";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "998989898");
            statement.setString(2, "Maria");
            statement.setString(3, "Chavez");
            statement.setString(4, "Jimenez");
            statement.setString(5, "Medellin");
            statement.setString(6, "777777");
            statement.setString(7, "loba@hotmail.com");
            statement.setString(8, "Femenino");
            statement.setInt(9, 28);
            statement.setDouble(10, 1.60);
            statement.setDouble(11, 80);

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

    public static void eliminar() {
        JdbcConnection connection = MySQLConnection.connect();
        String sql = "delete from cliente where identificacion = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "8853492");

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
