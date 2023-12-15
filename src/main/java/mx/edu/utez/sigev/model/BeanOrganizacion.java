package mx.edu.utez.sigev.model;

import java.io.Serializable;

public class BeanOrganizacion implements Serializable {
    private int idOrganizacion;
    private String rfc;
    private String nombreOrganizacion;
    private String razonSocial;
    private String imagenLogotipo;
    private int estatusOrganizacion;
    private BeanUsuario usuario;
    private BeanColor color;
    private BeanDireccion direccion;

    public BeanOrganizacion() {
    }

    public BeanOrganizacion(int idOrganizacion) {
        this.idOrganizacion = idOrganizacion;
    }

    public int getIdOrganizacion() {
        return idOrganizacion;
    }

    public void setIdOrganizacion(int idOrganizacion) {
        this.idOrganizacion = idOrganizacion;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombreOrganizacion() {
        return nombreOrganizacion;
    }

    public void setNombreOrganizacion(String nombreOrganizacion) {
        this.nombreOrganizacion = nombreOrganizacion;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getImagenLogotipo() {
        return imagenLogotipo;
    }

    public void setImagenLogotipo(String imagenLogotipo) {
        this.imagenLogotipo = imagenLogotipo;
    }

    public int getEstatusOrganizacion() {
        return estatusOrganizacion;
    }

    public void setEstatusOrganizacion(int estatusOrganizacion) {
        this.estatusOrganizacion = estatusOrganizacion;
    }

    public BeanUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(BeanUsuario usuario) {
        this.usuario = usuario;
    }

    public BeanColor getColor() {
        return color;
    }

    public void setColor(BeanColor color) {
        this.color = color;
    }

    public BeanDireccion getDireccion() {
        return direccion;
    }

    public void setDireccion(BeanDireccion direccion) {
        this.direccion = direccion;
    }
}
