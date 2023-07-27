package mx.edu.utez.sigev.model;


import java.io.Serializable;

public class Usuario  implements  Serializable {


    private int idUsuario;
    private String correo;
    private String contrasena;
    private String telefono;
    private String codigoRecuperacion;
    private int estatusUsuario;
    private Rol rol;

    public Usuario() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCodigoRecuperacion() {
        return codigoRecuperacion;
    }

    public void setCodigoRecuperacion(String codigoRecuperacion) {
        this.codigoRecuperacion = codigoRecuperacion;
    }

    public int getEstatusUsuario() {
        return estatusUsuario;
    }

    public void setEstatusUsuario(int estatusUsuario) {
        this.estatusUsuario = estatusUsuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}