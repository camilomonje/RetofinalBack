package com.foodka.back;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Prueba {

    public static void main(String[] args) {
        String fecha = "12-12-2022";
        String hora = "10:00";
        System.out.println(validateFecha(fecha, hora));
//        System.out.println(validateHora(hora));
    }

    public static boolean validateFecha(String dia, String hora) {
        try {
            Date fecha = new SimpleDateFormat("dd-M-yyyy").parse(dia);
            System.out.println(fecha);
            String fechanueva = new SimpleDateFormat("dd-M-yyyy").format(fecha);
            System.out.println("fecha ingresada " + dia);
            System.out.println("fecha nueva " +fechanueva);
            Date hora2 = new SimpleDateFormat("HH:mm").parse(hora);
            String horaNueva = new SimpleDateFormat("HH:mm").format(hora2);
            return (fechanueva.equals(dia) && horaNueva.equals(hora));
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public static boolean validateHora(String hora) {
        try {
            Date hora2 = new SimpleDateFormat("HH:mm").parse(hora);
            String horaNueva = new SimpleDateFormat("HH:mm").format(hora2);
            return (horaNueva.equals(hora));
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
