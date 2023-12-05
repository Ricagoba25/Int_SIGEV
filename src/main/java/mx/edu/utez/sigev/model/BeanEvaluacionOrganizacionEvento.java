package mx.edu.utez.sigev.model;

import java.io.Serializable;

public class BeanEvaluacionOrganizacionEvento implements Serializable {
    private int idEvaluacionOrganizacionEvento;
    private BeanEvaluacion evaluacion;
    private BeanOrganizacion organizacion;
    private BeanEvento evento;

    public BeanEvaluacionOrganizacionEvento() {
    }

    public int getIdEvaluacionOrganizacionEvento() {
        return idEvaluacionOrganizacionEvento;
    }

    public void setIdEvaluacionOrganizacionEvento(int idEvaluacionOrganizacionEvento) {
        this.idEvaluacionOrganizacionEvento = idEvaluacionOrganizacionEvento;
    }

    public BeanEvaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(BeanEvaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    public BeanOrganizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(BeanOrganizacion organizacion) {
        this.organizacion = organizacion;
    }

    public BeanEvento getEvento() {
        return evento;
    }

    public void setEvento(BeanEvento evento) {
        this.evento = evento;
    }
}
