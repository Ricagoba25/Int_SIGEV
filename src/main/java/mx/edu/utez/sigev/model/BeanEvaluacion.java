package mx.edu.utez.sigev.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BeanEvaluacion implements Serializable {
    private int idEvaluacion;
    private String nombreEvaluacion;
    private Date fechaRegistro = new Date();
    private int estatusEvaluacion;
    private BeanOrganizacion organizacion;
    private List<BeanPregunta> preguntas = new ArrayList<>();
    private List<BeanRespuesta> respuestas = new ArrayList<>();

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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<BeanPregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<BeanPregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public List<BeanRespuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<BeanRespuesta> respuestas) {
        this.respuestas = respuestas;
    }
}
