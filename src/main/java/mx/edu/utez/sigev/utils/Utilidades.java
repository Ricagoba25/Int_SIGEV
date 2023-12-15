package mx.edu.utez.sigev.utils;

import java.util.Date;

public class Utilidades {

    public int numeroInt(String numero) {
        try {
            if (numero != null)
                return Integer.parseInt(numero);
            else
                return 0;
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }

    public Date validaFecha(String fecha){
        try {
            if (fecha != null)
                return new Date(fecha);
            else
                return new Date();
        } catch (NumberFormatException e) {
            return new Date();
        }

    }
}
