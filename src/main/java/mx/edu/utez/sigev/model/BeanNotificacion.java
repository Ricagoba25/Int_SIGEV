package mx.edu.utez.sigev.model;

public class BeanNotificacion {
    int idNotificacion;
    String mensaje;
    int estatusNotificacion;

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
