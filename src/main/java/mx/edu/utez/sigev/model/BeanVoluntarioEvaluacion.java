package mx.edu.utez.sigev.model;

import java.io.Serializable;

public class BeanVoluntarioEvaluacion implements Serializable {
    private int iVoluntarioEvaluacion;
    private BeanVoluntario voluntario;
    private BeanEvaluacionOrganizacionEvento evaluacionOrganizacionEvento;
    private int estatusVoluntarioEvaluacion;

    public BeanVoluntarioEvaluacion() {
    }

    public int getiVoluntarioEvaluacion() {
        return iVoluntarioEvaluacion;
    }

    public void setiVoluntarioEvaluacion(int iVoluntarioEvaluacion) {
        this.iVoluntarioEvaluacion = iVoluntarioEvaluacion;
    }

    public BeanVoluntario getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(BeanVoluntario voluntario) {
        this.voluntario = voluntario;
    }

    public int getEstatusVoluntarioEvaluacion() {
        return estatusVoluntarioEvaluacion;
    }

    public void setEstatusVoluntarioEvaluacion(int estatusVoluntarioEvaluacion) {
        this.estatusVoluntarioEvaluacion = estatusVoluntarioEvaluacion;
    }

    public BeanEvaluacionOrganizacionEvento getEvaluacionOrganizacionEvento() {
        return evaluacionOrganizacionEvento;
    }

    public void setEvaluacionOrganizacionEvento(BeanEvaluacionOrganizacionEvento evaluacionOrganizacionEvento) {
        this.evaluacionOrganizacionEvento = evaluacionOrganizacionEvento;
    }
}
