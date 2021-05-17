/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntodeventa.jdbc;

import com.mysql.cj.jdbc.JdbcConnection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author abarrios
 */
public class MySQLConnection {

    private static transient JdbcConnection connection;
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/dbpuntodeventa?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrival=true";
    private static final String user = "root";
    private static final String password = "Cobiscorp2020*";

    public MySQLConnection() {
        connection = null;
    }

    public MySQLConnection(JdbcConnection con) {
        connection = con;
    }

    public static void setConnection(JdbcConnection con) {
        connection = con;
    }

    public static JdbcConnection getConnection() {
        return connection;
    }

    public static boolean isConnected() {
        return connection != null;
    }

    public static void closeConnection() {
        try {
            if (isConnected()) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    public static JdbcConnection connect() {
        connection = null;
        try {
            Class.forName(driver);
            connection = (JdbcConnection) DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            System.err.println("Error: " + ex.getMessage());
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        } finally {
            return connection;
        }
    }
}
