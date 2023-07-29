package mx.edu.utez.sigev.model;

import java.io.Serializable;

public class BeanColor implements Serializable {
    private int idColor;
    private String nombreColor;
    private String codigo;

    public BeanColor() {
    }

    public int getIdColor() {
        return idColor;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }

    public String getNombreColor() {
        return nombreColor;
    }

    public void setNombreColor(String nombreColor) {
        this.nombreColor = nombreColor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
