/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntodeventa.consecutivo;

import com.puntodeventa.control.ControlUtilidades;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author abarrios
 */
public class ConsecutivoRestaurante {

    public ConsecutivoRestaurante() {

    }

    public static void main(String[] args) throws IOException {
        System.out.println(ControlUtilidades.numeroConsecutivo());
        JOptionPane.showMessageDialog(null, ControlUtilidades.numeroConsecutivo());
        ControlUtilidades.guardarConsecutivo(ControlUtilidades.numeroConsecutivo());
    }
}
