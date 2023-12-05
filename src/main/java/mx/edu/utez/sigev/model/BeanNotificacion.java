package mx.edu.utez.sigev.model;

import java.io.Serializable;

public class BeanNotificacion implements Serializable {
    private int idNotificacion;
    private String mensaje;
    private int estatusNotificacion;

    public BeanNotificacion() {
    }

    public int getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getEstatusNotificacion() {
        return estatusNotificacion;
    }

    public void setEstatusNotificacion(int estatusNotificacion) {
        this.estatusNotificacion = estatusNotificacion;
    }
}
