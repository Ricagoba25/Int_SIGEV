package mx.edu.utez.sigev.model;

import java.io.Serializable;
import java.util.Date;

public class BeanEvento implements Serializable {
    private int idEvento;
    private String nombreEvento;
    private String descripcion;
    private String fecha;
    private int estatusEvento;
    private BeanDireccion direccion;

    public BeanEvento() {
    }

    public BeanEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getEstatusEvento() {
        return estatusEvento;
    }

    public void setEstatusEvento(int estatusEvento) {
        this.estatusEvento = estatusEvento;
    }

    public BeanDireccion getDireccion() {
        return direccion;
    }

    public void setDireccion(BeanDireccion direccion) {
        this.direccion = direccion;
    }
}
