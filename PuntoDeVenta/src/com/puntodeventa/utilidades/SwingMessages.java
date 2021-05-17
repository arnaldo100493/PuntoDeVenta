/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntodeventa.utilidades;

import javax.swing.JOptionPane;

/**
 *
 * @author abarrios
 */
public class SwingMessages {

    public SwingMessages() {

    }

    public static void mostrarDialogoMensajeSencillo(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.PLAIN_MESSAGE);
    }

    public static void mostrarDialogoMensajeInformacion(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mostrarDialogoMensajeAdvertencia(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Adverencia", JOptionPane.WARNING_MESSAGE);
    }

    public static void mostrarDialogoMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static int mostrarDialogoMensajeConfirmacion(String mensaje) {
        int confirmacion = JOptionPane.showConfirmDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return confirmacion;
    }

    public static int mostrarDialogoMensajeOpcion(String mensaje) {
        int opcion = JOptionPane.showOptionDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"SI", "NO"}, "SI");
        return opcion;
    }
}
