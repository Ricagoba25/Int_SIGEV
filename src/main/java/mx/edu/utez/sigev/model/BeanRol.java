package mx.edu.utez.sigev.model;


import java.io.Serializable;

public class BeanRol implements Serializable {
    private int idRol;
    private String nombreRol;

    public BeanRol() {
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}