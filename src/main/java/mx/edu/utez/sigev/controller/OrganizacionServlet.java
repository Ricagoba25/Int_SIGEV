package mx.edu.utez.sigev.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import mx.edu.utez.sigev.model.*;
import mx.edu.utez.sigev.model.DAO.DaoDireccion;
import mx.edu.utez.sigev.model.DAO.DaoOrganizacion;
import mx.edu.utez.sigev.model.DAO.DaoUsuario;
import mx.edu.utez.sigev.utils.Utilidades;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "OrganizacionServlet", value = "/organizacion")
public class OrganizacionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Utilidades utilidades = new Utilidades();
        // Crear una respuesta en formato JSON
        JsonObject jsonResponse = new JsonObject();

        // Obtener los datos del formulario
        String accion = request.getParameter("accion");

        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        String telefono = request.getParameter("telefono");
        String rfc = request.getParameter("rfc");
        String nombreOrganizacion = request.getParameter("nombreOrganizacion");
        String razonSocial = request.getParameter("razonSocial");
        String calle = request.getParameter("calle");
        String colonia = request.getParameter("colonia");
        String municipio = request.getParameter("municipio");
        String noExterior = request.getParameter("noExterior");
        String noInterior = request.getParameter("noInterior");
        int idEstado = utilidades.numeroInt(request.getParameter("idEstado"));
        int idColor = utilidades.numeroInt(request.getParameter("idColor"));
        int idUsuario = utilidades.numeroInt(request.getParameter("idUsuario"));
        int idPersona = utilidades.numeroInt(request.getParameter("idPersona"));
        int idDireccion = utilidades.numeroInt(request.getParameter("idDireccion"));
        int idOrganizacion = utilidades.numeroInt(request.getParameter("idOrganizacion"));
        int estatus = utilidades.numeroInt(request.getParameter("estatus"));

        BeanUsuario usuario = new BeanUsuario();
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);
        usuario.setTelefono(telefono);
        usuario.setRol(new BeanRol(2));

        BeanDireccion direccion = new BeanDireccion();
        direccion.setCalle(calle);
        direccion.setColonia(colonia);
        direccion.setMunicipio(municipio);
        direccion.setNoExterior(noExterior);
        direccion.setNoInterior(noInterior);
        direccion.setEstado(new BeanEstado(idEstado));

        BeanOrganizacion organizacion = new BeanOrganizacion();
        organizacion.setRfc(rfc);
        organizacion.setNombreOrganizacion(nombreOrganizacion);
        organizacion.setRazonSocial(razonSocial);
        organizacion.setImagenLogotipo(null);
        organizacion.setColor(new BeanColor(1));

        DaoUsuario daoUsuario = new DaoUsuario();
        DaoDireccion daoDireccion = new DaoDireccion();
        DaoOrganizacion daoOrganizacion = new DaoOrganizacion();
        Boolean respuesta;


        switch (accion) {
            case "registrar":
                respuesta = daoUsuario.insert(usuario);
                System.out.println("resUsuario " + respuesta);
                if (respuesta) {
                    usuario = (BeanUsuario) daoUsuario.findbyCorreo(correo);
                    organizacion.setUsuario(usuario);
                    idDireccion = daoDireccion.registrar(direccion);

                    System.out.println(direccion);

                    System.out.println("resDireccion " + idDireccion);


                    if (idDireccion > 0) {
                        direccion.setIdDireccion(idDireccion);
                        organizacion.setDireccion(direccion);
                        respuesta = daoOrganizacion.insert(organizacion);
                        System.out.println("resOrganizacion " + respuesta);
                        if (respuesta) {
                            jsonResponse.addProperty("error", 0);
                            jsonResponse.addProperty("title", "");
                            jsonResponse.addProperty("message", "Organización registrada exitosamente");
                        } else {
                            jsonResponse.addProperty("error", 1);
                            jsonResponse.addProperty("title", "Organización no registrada, problemas en organización");
                        }
                    } else {
                        jsonResponse.addProperty("error", 1);
                        jsonResponse.addProperty("title", "Organización no registrada, problemas en direccion");
                    }
                } else {
                    jsonResponse.addProperty("error", 1);
                    jsonResponse.addProperty("title", "Organización no registrada, problemas en usuario");
                }
                break;
            case "modificar":
                usuario.setIdUsuario(idUsuario);
                organizacion.setIdOrganizacion(idOrganizacion);

                respuesta = daoUsuario.update(usuario.getIdUsuario(), usuario);

                System.out.println("resUsuario " + respuesta);
                if (respuesta) {

                    respuesta = daoOrganizacion.update(organizacion.getIdOrganizacion(), organizacion);
                    System.out.println("resOrganizacion " + respuesta);

                    //update session vars
                    BeanOrganizacion organizacionSesion = (BeanOrganizacion) daoOrganizacion.findOne(usuario.getIdUsuario());
                    request.getSession().setAttribute("sesion", organizacionSesion);

                    if (respuesta) {
                        jsonResponse.addProperty("error", 0);
                        jsonResponse.addProperty("title", "");
                        jsonResponse.addProperty("newGetNombreCompleto", organizacionSesion.getNombreOrganizacion());
                        jsonResponse.addProperty("message", "Organización modificada exitosamente");
                    } else {
                        jsonResponse.addProperty("error", 1);
                        jsonResponse.addProperty("title", "Organización no modificada, problemas en organizacion");
                    }
                } else {
                    jsonResponse.addProperty("error", 1);
                    jsonResponse.addProperty("title", "Organización no modificada, problemas en usuario");
                }
                break;
            case "eliminar":
                respuesta = daoUsuario.delete(idUsuario);

                System.out.println("resUsuario " + respuesta);
                if (respuesta) {
                    jsonResponse.addProperty("error", 0);
                    jsonResponse.addProperty("title", "");
                    jsonResponse.addProperty("message", "Organización eliminada exitosamente");
                } else {
                    jsonResponse.addProperty("error", 1);
                    jsonResponse.addProperty("title", "Organización no eliminada");
                }
                break;
            case "changeStatus":
                respuesta = daoOrganizacion.changeStatus(idOrganizacion, estatus);

                System.out.println("resUsuario " + respuesta);
                if (respuesta) {
                    jsonResponse.addProperty("error", 0);
                    jsonResponse.addProperty("title", "");
                    jsonResponse.addProperty("message", "Estatus de la organización modificado exitosamente");
                } else {
                    jsonResponse.addProperty("error", 1);
                    jsonResponse.addProperty("title", "El estatus de la organización no se cambio");
                }
                break;
            default:
                jsonResponse.addProperty("error", 1);
                jsonResponse.addProperty("title", "Acción no encontrada");
                jsonResponse.addProperty("message", "Parámetros incorrectos");
                break;
        }

        // Establecer el tipo de contenido de la respuesta a JSON
        response.setContentType("application/json");

        // Enviar la respuesta al cliente
        response.getWriter().write(jsonResponse.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        DaoOrganizacion daoOrganizacion = new DaoOrganizacion();
        List<BeanOrganizacion> listaOrganizaciones = new ArrayList<>();

        try {
            listaOrganizaciones = daoOrganizacion.findAll();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String json = gson.toJson(listaOrganizaciones);

        resp.setContentType("text/json");
        resp.getWriter().write(json);
    }
}