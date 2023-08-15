package mx.edu.utez.sigev.utils;

public class Utilidades {

    public Utilidades() {
    }

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
}
