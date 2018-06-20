/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.herramientas;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Nacho
 */
public class Utilidad {

    public static String formatFecha(Date fecha){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        return sdf.format(fecha);
    }
    
    
    // validador de RUTs
    public static boolean validaRut(String rut) {

        String[] lstring = rut.split("-");
        if (lstring.length != 2) {
            return false;
        } else if (lstring[1].length() > 1) {
            return false;
        }
        int suma = 0;
        int[] serie = {2, 3, 4, 5, 6, 7};
        int contadors = 0;
        for (int i = lstring[0].toCharArray().length - 1; i >= 0; i--) {
            if (lstring[0].toCharArray()[i] == '0'
                    || lstring[0].toCharArray()[i] == '1'
                    || lstring[0].toCharArray()[i] == '2'
                    || lstring[0].toCharArray()[i] == '3'
                    || lstring[0].toCharArray()[i] == '4'
                    || lstring[0].toCharArray()[i] == '5'
                    || lstring[0].toCharArray()[i] == '6'
                    || lstring[0].toCharArray()[i] == '7'
                    || lstring[0].toCharArray()[i] == '8'
                    || lstring[0].toCharArray()[i] == '9') {
                suma += serie[contadors] * Integer.parseInt("" + lstring[0].toCharArray()[i]);
                contadors++;
                if (contadors > 5) {
                    contadors = 0;
                }
            }
        }
        int cverif = 11 - (Math.abs(suma % 11));
        if (lstring[1].toCharArray()[0] == '0'
                || lstring[1].toCharArray()[0] == '1'
                || lstring[1].toCharArray()[0] == '2'
                || lstring[1].toCharArray()[0] == '3'
                || lstring[1].toCharArray()[0] == '4'
                || lstring[1].toCharArray()[0] == '5'
                || lstring[1].toCharArray()[0] == '6'
                || lstring[1].toCharArray()[0] == '7'
                || lstring[1].toCharArray()[0] == '8'
                || lstring[1].toCharArray()[0] == '9') {

            if (cverif == 11) {
                return Integer.parseInt(lstring[1]) == 0;
            }
            return Integer.parseInt(lstring[1]) == cverif;
        }

        if (cverif == 10) {
            return lstring[1].equalsIgnoreCase("k");
        }
        return false;
    }

    //metodo que formatea los precios
    public static String formatValor(Integer precio) {
        String sprecio = precio.toString();
        if (sprecio.length() > 3) {
            String nprecio = "";
            int plus = -1;
            for (int i = sprecio.toCharArray().length - 1; i >= 0; i--) {
                plus++;
                if (plus == 3) {
                    nprecio = nprecio + "." + sprecio.toCharArray()[i];
                    plus = 0;
                } else {
                    nprecio = nprecio + sprecio.toCharArray()[i];
                }
            }
            return inversoValor(nprecio);
        }
        return sprecio;
    }

    // Para ingresar ruts a la BD
    public static String formatRutIngreso(String rut) {
        String[] lstring = rut.split("-");
        if (lstring.length != 2) {
            return "";
        } else if (lstring[1].length() > 1) {
            return "";
        } else {
            String rut2 = "";
            for (char c : lstring[0].toCharArray()) {
                if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
                    rut2 = rut2 + c;
                }
            }
            return rut2 + "-" + lstring[1];
        }
    }
    
    // Para formatear ruts desde la BD
    public static String formatRutSalida(String rut) {
        String[] lstring = rut.split("-");
        if (lstring.length != 2) {
            return "";
        } else if (lstring[1].length() > 1) {
            return "";
        } else {
            String rut2 = "";
            for (char c : lstring[0].toCharArray()) {
                if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
                    rut2 = rut2 + c;
                }
            }
            return formatValor(Integer.parseInt(rut2)) + "-" + lstring[1];
        }
    }

    // Pruebas de funcionamiento
    public static void main(String[] args) {
        System.out.println("Número formateado " + formatValor(700));
        System.out.println(validaRut("Rut002"));
        System.out.println(formatRutIngreso("111111111"));
        System.out.println(formatRutSalida("16209161-1"));
    }

    // necesario para formatValor()
    private static String inversoValor(String nprecio) {
        String nprecio2 = "";
        for (int i = nprecio.toCharArray().length - 1; i >= 0; i--) {
            nprecio2 = nprecio2 + nprecio.toCharArray()[i];
        }

        return nprecio2;
    }

}
