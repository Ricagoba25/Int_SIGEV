package mx.edu.utez.sigev.model;

import java.io.Serializable;

public class BeanEventoVoluntario implements Serializable  {
    private int voluntario_idVoluntario;
    private int evento_idEvento;
    private int estatusSolicitud;

    public BeanEventoVoluntario() {
    }

    public BeanEventoVoluntario(int voluntario_idVoluntario, int evento_idEvento, int estatusSolicitud) {
        this.voluntario_idVoluntario = voluntario_idVoluntario;
        this.evento_idEvento = evento_idEvento;
        this.estatusSolicitud = estatusSolicitud;
    }

    public int getVoluntario_idVoluntario() {
        return voluntario_idVoluntario;
    }

    public void setVoluntario_idVoluntario(int voluntario_idVoluntario) {
        this.voluntario_idVoluntario = voluntario_idVoluntario;
    }

    public int getEvento_idEvento() {
        return evento_idEvento;
    }

    public void setEvento_idEvento(int evento_idEvento) {
        this.evento_idEvento = evento_idEvento;
    }

    public int getEstatusSolicitud() {
        return estatusSolicitud;
    }

    public void setEstatusSolicitud(int estatusSolicitud) {
        this.estatusSolicitud = estatusSolicitud;
    }
}
