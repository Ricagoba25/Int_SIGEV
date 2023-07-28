package mx.edu.utez.sigev.model;

import java.util.Date;

public class BeanEvento {
    int idEvento;
    String nombreEvento;
    String descripcion;
    Date fecha;
    int estatusEvento;

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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getEstatusEvento() {
        return estatusEvento;
    }

    public void setEstatusEvento(int estatusEvento) {
        this.estatusEvento = estatusEvento;
    }
}
