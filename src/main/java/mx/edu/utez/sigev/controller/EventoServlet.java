package mx.edu.utez.sigev.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import mx.edu.utez.sigev.model.*;
import mx.edu.utez.sigev.model.DAO.DaoDireccion;
import mx.edu.utez.sigev.model.DAO.DaoEvento;
import mx.edu.utez.sigev.utils.Utilidades;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "EventoServlet", value = "/evento")
public class EventoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Utilidades utilidades = new Utilidades();
        // Crear una respuesta en formato JSON
        JsonObject jsonResponse = new JsonObject();

        // Obtener los datos del formulario
        String accion = request.getParameter("accion");

        String nombreEvento = request.getParameter("nombreEvento");
        String descripcion = request.getParameter("descripcion");
        Date fecha = utilidades.validaFecha("fecha");
        String calle = request.getParameter("calle");
        String colonia = request.getParameter("colonia");
        String municipio = request.getParameter("municipio");
        String noExterior = request.getParameter("noExterior");
        String noInterior = request.getParameter("noInterior");
        int idEstado = utilidades.numeroInt(request.getParameter("idEstado"));
        int idDireccion = utilidades.numeroInt(request.getParameter("idDireccion"));
        int idOrganizacion = utilidades.numeroInt(request.getParameter("idOrganizacion"));
        int idEvaluacion = 1;
        int idEvento = utilidades.numeroInt(request.getParameter("idEvento"));
        int estatusEvento = utilidades.numeroInt(request.getParameter("estatusEvento"));

        BeanDireccion direccion = new BeanDireccion();
        direccion.setCalle(calle);
        direccion.setColonia(colonia);
        direccion.setMunicipio(municipio);
        direccion.setNoExterior(noExterior);
        direccion.setNoInterior(noInterior);
        direccion.setEstado(new BeanEstado(idEstado));

        BeanEvento evento = new BeanEvento();
        evento.setNombreEvento(nombreEvento);
        evento.setDescripcion(descripcion);
        evento.setFecha(fecha);

        BeanOrganizacion organizacion = new BeanOrganizacion(idOrganizacion);
        BeanEvaluacion evaluacion = new BeanEvaluacion(idEvaluacion);

        DaoDireccion daoDireccion = new DaoDireccion();
        DaoEvento daoEvento = new DaoEvento();
        Boolean respuesta;


        switch (accion) {
            case "registrar":
                idDireccion = daoDireccion.registrar(direccion);

                System.out.println(direccion);

                System.out.println("resDireccion " + idDireccion);
                if (idDireccion > 0) {
                    direccion.setIdDireccion(idDireccion);
                    evento.setDireccion(direccion);
                    idEvento = daoEvento.registrar(evento);

                    System.out.println(evento);

                    System.out.println("resDireccion " + idEvento);
                    if (idEvento > 0) {
                        evento.setIdEvento(idEvento);
                        respuesta = daoEvento.registrarEvaluacionOrganizacionEvento(evaluacion, organizacion, evento);
                        System.out.println("resEvaluacionOrganizacionEvento " + respuesta);
                        if (respuesta) {
                            jsonResponse.addProperty("error", 0);
                            jsonResponse.addProperty("title", "");
                            jsonResponse.addProperty("message", "Evento registrado exitosamente");
                        } else {
                            jsonResponse.addProperty("error", 1);
                            jsonResponse.addProperty("title", "Evento no registrado, problemas en evaluacionOrganizacionEvento");
                        }
                    } else {
                        jsonResponse.addProperty("error", 1);
                        jsonResponse.addProperty("title", "Evento no registrado, problemas en evento");
                    }
                } else {
                    jsonResponse.addProperty("error", 1);
                    jsonResponse.addProperty("title", "Evento no registrado, problemas en direccion");
                }
                break;
            case "modificar":
                direccion.setIdDireccion(idDireccion);
                evento.setIdEvento(idEvento);

                respuesta = daoDireccion.update(direccion.getIdDireccion(), daoDireccion);

                System.out.println("resDireccion " + respuesta);
                if (respuesta) {
                    respuesta = daoEvento.update(evento.getIdEvento(), evento);
                    System.out.println("resEvento " + respuesta);
                    if (respuesta) {
                        jsonResponse.addProperty("error", 0);
                        jsonResponse.addProperty("title", "");
                        jsonResponse.addProperty("message", "Evento modificado exitosamente");
                    } else {
                        jsonResponse.addProperty("error", 1);
                        jsonResponse.addProperty("title", "Evento no modificado, problemas en evento");
                    }
                } else {
                    jsonResponse.addProperty("error", 1);
                    jsonResponse.addProperty("title", "Evento no modificado, problemas en dirección");
                }
                break;
            case "changeStatus":
                respuesta = daoEvento.changeStatus(idOrganizacion, estatusEvento);

                System.out.println("resUsuario " + respuesta);
                if (respuesta) {
                    jsonResponse.addProperty("error", 0);
                    jsonResponse.addProperty("title", "");
                    jsonResponse.addProperty("message", "Estatus del evento modificado exitosamente");
                } else {
                    jsonResponse.addProperty("error", 1);
                    jsonResponse.addProperty("title", "El estatus del evento no se cambio");
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
        DaoEvento daoEvento = new DaoEvento();

        List<BeanEvento> listaEventos = new ArrayList<>();

        try {
            listaEventos = daoEvento.findAll();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String json = gson.toJson(listaEventos);
        resp.setContentType("text/json");
        resp.getWriter().write(json);
    }
}
