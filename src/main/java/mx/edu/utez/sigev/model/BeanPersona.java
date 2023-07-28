package mx.edu.utez.sigev.model;

public class BeanPersona {
    private int idPersona;
    private String nombrePersona;
    private String primerApellido;
    private String segundoApellido;

    public BeanPersona() {
    }

    public BeanPersona(int idPersona, String nombrePersona, String primerApellido, String segundoApellido) {
        this.idPersona = idPersona;
        this.nombrePersona = nombrePersona;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
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
}
