/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntodeventa.utilidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author abarrios
 */
public class ValidarFormatoCorreo {

    public ValidarFormatoCorreo() {

    }

    public static boolean validarFormatoCorreo(String correo) {
        Pattern pattern = null;
        Matcher matcher = null;
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$";
        //"^[_a-z0-9-]+(\\\\.[_a-z0-9-]+)+@[a-z0-9-]+(\\\\.[a-z0-9-]+).(\\\\.[a-z]{2,4})$"
        //"^[_A-Za-z0-9-\\+]+(\\.[_A-Z-a-z0-9-]+])+@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+).(\\.[A-Za-z]{2,})$";
        //"^([0-9a-zA-Z]([_.w]+[0-9a-zA-Z])+(@[0-9a-zA-Z][-w].[0-9a-zA-Z].)+((a-zA-Z){2, 9}.)+[a-zA-Z]{2,3})$"
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(correo);

        if (matcher.find()) {
            //System.out.println("[" + matcher.group() + "]");
            return true;
        } else {
            return false;
        }

        /*if (matcher.matches()) {
            //System.out.println("[" + matcher.group() + "]");
            return true;
        } else {
            return false;
        }*/
    }

}
