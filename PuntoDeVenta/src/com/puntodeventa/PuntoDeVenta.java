/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntodeventa;

import com.jtattoo.plaf.bernstein.BernsteinLookAndFeel;
import com.puntodeventa.vista.VistaPuntoDeVenta;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author abarrios
 */
public class PuntoDeVenta {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        // TODO code application logic here
        UIManager.setLookAndFeel(new BernsteinLookAndFeel());

        new VistaPuntoDeVenta().setVisible(true);
    }

}
