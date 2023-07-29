package mx.edu.utez.sigev.model;

import java.io.Serializable;

public class BeanEstado implements Serializable {
    private int idEstado;
    private String nombre;

    public BeanEstado() {
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
