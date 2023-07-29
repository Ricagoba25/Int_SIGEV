package mx.edu.utez.sigev.model;

import java.io.Serializable;

public class BeanPersona implements Serializable {
    private int idPersona;
    private String nombrePersona;
    private String primerApellido;
    private String segundoApellido;
    private BeanUsuario usuario;

    public BeanPersona() {
    }

    public String getNombreCompleto() {
        if (getSegundoApellido() != null) {
            return getNombrePersona() + " " + getPrimerApellido() + " " + getSegundoApellido();
        }
        return getNombrePersona() + " " + getPrimerApellido();
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public BeanUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(BeanUsuario usuario) {
        this.usuario = usuario;
    }
}
