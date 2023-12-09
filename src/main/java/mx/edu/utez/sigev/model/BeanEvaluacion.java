package mx.edu.utez.sigev.model;

import java.io.Serializable;

public class BeanEvaluacion implements Serializable {
    private int idEvaluacion;
    private String nombreEvaluacion;
    private int estatusEvaluacion;
    private BeanOrganizacion organizacion;

    public BeanEvaluacion() {
    }

    public BeanEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public String getNombreEvaluacion() {
        return nombreEvaluacion;
    }

    public void setNombreEvaluacion(String nombreEvaluacion) {
        this.nombreEvaluacion = nombreEvaluacion;
    }

    public int getEstatusEvaluacion() {
        return estatusEvaluacion;
    }

    public void setEstatusEvaluacion(int estatusEvaluacion) {
        this.estatusEvaluacion = estatusEvaluacion;
    }

    public BeanOrganizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(BeanOrganizacion organizacion) {
        this.organizacion = organizacion;
    }
}
