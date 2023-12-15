package mx.edu.utez.sigev.model;

import java.io.Serializable;

public class BeanPregunta implements Serializable {
    private int idPregunta;
    private String textoPregunta;
    private BeanEvaluacion evaluacion;

    public BeanPregunta() {
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getTextoPregunta() {
        return textoPregunta;
    }

    public void setTextoPregunta(String textoPregunta) {
        this.textoPregunta = textoPregunta;
    }

    public BeanEvaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(BeanEvaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }
}
