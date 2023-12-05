package mx.edu.utez.sigev.model;

import java.io.Serializable;

public class BeanRespuesta implements Serializable {
    private int idRespuesta;
    private String textoRespuesta;
    private BeanPregunta pregunta;

    public BeanRespuesta() {
    }

    public int getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public String getTextoRespuesta() {
        return textoRespuesta;
    }

    public void setTextoRespuesta(String textoRespuesta) {
        this.textoRespuesta = textoRespuesta;
    }

    public BeanPregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(BeanPregunta pregunta) {
        this.pregunta = pregunta;
    }
}
