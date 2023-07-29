package mx.edu.utez.sigev.model;

import java.io.Serializable;

public class BeanVoluntario implements Serializable {
    private int idVoluntario;
    private String curp;
    private int estatusVoluntario;
    private BeanPersona persona;

    public BeanVoluntario() {
    }

    public int getIdVoluntario() {
        return idVoluntario;
    }

    public void setIdVoluntario(int idVoluntario) {
        this.idVoluntario = idVoluntario;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public int getEstatusVoluntario() {
        return estatusVoluntario;
    }

    public void setEstatusVoluntario(int estatusVoluntario) {
        this.estatusVoluntario = estatusVoluntario;
    }

    public BeanPersona getPersona() {
        return persona;
    }

    public void setPersona(BeanPersona persona) {
        this.persona = persona;
    }
}