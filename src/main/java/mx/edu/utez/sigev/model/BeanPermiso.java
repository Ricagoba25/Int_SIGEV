package mx.edu.utez.sigev.model;

import java.io.Serializable;

public class BeanPermiso implements Serializable {
    private int idPermiso;
    private String nombrePermiso;

    public BeanPermiso() {
    }

    public int getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(int idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getNombrePermiso() {
        return nombrePermiso;
    }

    public void setNombrePermiso(String nombrePermiso) {
        this.nombrePermiso = nombrePermiso;
    }
}
