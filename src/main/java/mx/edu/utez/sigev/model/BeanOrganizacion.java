package mx.edu.utez.sigev.model;

public class BeanOrganizacion {
    int idOrganizacion;
    String rfc;
    String nombreOrganizacion;
    String razonSocial;
    String imagenLogotipo;
    int estatusOrganizacion;


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

    public void setEstatusOrganizacion(String estatusOrganizacion) {
        this.estatusOrganizacion = Integer.parseInt(estatusOrganizacion);
    }
}
