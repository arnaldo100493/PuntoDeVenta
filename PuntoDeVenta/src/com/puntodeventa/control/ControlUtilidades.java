/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntodeventa.control;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author abarrios
 */
public class ControlUtilidades implements Serializable {

    private static Integer consecutivo;
    private static Calendar calendar;

    public ControlUtilidades() {
        ControlUtilidades.consecutivo = null;
        ControlUtilidades.calendar = new GregorianCalendar();
    }

    public static String fechaActual() {
        return ControlUtilidades.calendar.get(Calendar.DAY_OF_MONTH) + "/" + (ControlUtilidades.calendar.get(Calendar.MONTH) + 1) + "/" + ControlUtilidades.calendar.get(Calendar.YEAR);
    }

    public static void iniciarConsecutivo() {
        try {
            ControlUtilidades.consecutivo = (Integer) com.puntodeventa.control.ControlArchivo.leerObjeto("Consecutivo Restaurante");
            if (ControlUtilidades.consecutivo == null) {
                ControlUtilidades.consecutivo = 0000;
            }
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "\nError al leer el archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Integer numeroConsecutivo() {
        iniciarConsecutivo();
        if (ControlUtilidades.consecutivo != null) {
            return ControlUtilidades.consecutivo + 1;
        }
        return null;
    }

    public static void guardarConsecutivo(Integer con) {
        if (con != null) {
            try {
                ControlUtilidades.consecutivo = con;
                ControlArchivo.escribirObjeto(ControlUtilidades.consecutivo, "Consecutivo Restaurante");
            } catch (IOException ex) {
                Logger.getLogger(ControlVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
