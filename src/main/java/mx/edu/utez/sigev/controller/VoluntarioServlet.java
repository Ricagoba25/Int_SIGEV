package mx.edu.utez.sigev.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import mx.edu.utez.sigev.model.*;
import mx.edu.utez.sigev.model.DAO.DaoPersona;
import mx.edu.utez.sigev.model.DAO.DaoRespuesta;
import mx.edu.utez.sigev.model.DAO.DaoUsuario;
import mx.edu.utez.sigev.model.DAO.DaoVoluntario;
import mx.edu.utez.sigev.utils.Utilidades;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "VoluntarioServlet", value = "/voluntario")
public class VoluntarioServlet extends HttpServlet {
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
        String nombrePersona = request.getParameter("nombrePersona");
        String primerapellido = request.getParameter("primerApellido");
        String segundoapellido = request.getParameter("segundoApellido");
        String curp = request.getParameter("curp");
        int idUsuario = utilidades.numeroInt(request.getParameter("idUsuario"));
        int idPersona = utilidades.numeroInt(request.getParameter("idPersona"));
        int idVoluntario = utilidades.numeroInt(request.getParameter("idVoluntario"));
        int idEvaluacionOrganizacionEvento = utilidades.numeroInt(request.getParameter("idEvaluacionOrganizacionEvento"));
        int idEstatusAceptadoRechazado = utilidades.numeroInt(request.getParameter("idEstatusAceptadoRechazado"));

        String[] idPreguntas = request.getParameterValues("idPreguntas[]");
        String[] respuestas = request.getParameterValues("respuestas[]");

        BeanUsuario usuario = new BeanUsuario();
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);
        usuario.setTelefono(telefono);
        usuario.setRol(new BeanRol(3));

        BeanPersona persona = new BeanPersona();
        persona.setNombrePersona(nombrePersona);
        persona.setPrimerApellido(primerapellido);
        persona.setSegundoApellido(segundoapellido);

        BeanVoluntario voluntario = new BeanVoluntario();
        voluntario.setCurp(curp);

        DaoUsuario daoUsuario = new DaoUsuario();
        DaoPersona daoPersona = new DaoPersona();
        DaoVoluntario daoVoluntario = new DaoVoluntario();
        Boolean respuesta = false;

        switch (accion) {
            case "registrar":
                respuesta = daoUsuario.insert(usuario);

                System.out.println("resUsuario " + respuesta);
                if (respuesta) {
                    usuario = (BeanUsuario) daoUsuario.findbyCorreo(correo);
                    persona.setUsuario(usuario);
                    respuesta = daoPersona.insert(persona);
                    System.out.println("resPersona " + respuesta);
                    if (respuesta) {
                        persona = (BeanPersona) daoPersona.findOne(usuario.getIdUsuario());
                        voluntario.setPersona(persona);
                        respuesta = daoVoluntario.insert(voluntario);
                        System.out.println("resVoluntario " + respuesta);
                        if (respuesta) {


                            jsonResponse.addProperty("error", 0);
                            jsonResponse.addProperty("title", "");
                            jsonResponse.addProperty("message", "Voluntario registrado exitosamente");

                        } else {
                            jsonResponse.addProperty("error", 1);
                            jsonResponse.addProperty("title", "Voluntario no registrado, problemas en persona");
                        }
                    } else {
                        jsonResponse.addProperty("error", 1);
                        jsonResponse.addProperty("title", "Voluntario no registrado, problemas en persona");
                    }
                } else {
                    jsonResponse.addProperty("error", 1);
                    jsonResponse.addProperty("title", "Voluntario no registrado, problemas en usuario");
                }
                break;
            case "modificar":

                usuario.setIdUsuario(idUsuario);
                persona.setIdPersona(idPersona);
                voluntario.setIdVoluntario(idVoluntario);

                respuesta = daoUsuario.update(usuario.getIdUsuario(), usuario);

                System.out.println("resUsuario " + respuesta);
                if (respuesta) {
                    respuesta = daoPersona.update(persona.getIdPersona(), persona);
                    System.out.println("resPersona " + respuesta);
                    if (respuesta) {
                        respuesta = daoVoluntario.update(voluntario.getIdVoluntario(), voluntario);
                        System.out.println("resVoluntario " + respuesta);
                        if (respuesta) {

                            //actualizar session vars
                            BeanVoluntario voluntarioSesion = (BeanVoluntario) daoVoluntario.findOne(usuario.getIdUsuario());
                            request.getSession().setAttribute("sesion", voluntarioSesion);


                            jsonResponse.addProperty("error", 0);
                            jsonResponse.addProperty("newGetNombreCompleto", voluntarioSesion.getPersona().getNombreCompleto());
                            jsonResponse.addProperty("title", "");
                            jsonResponse.addProperty("message", "Voluntario modificado exitosamente");
                        } else {
                            jsonResponse.addProperty("error", 1);
                            jsonResponse.addProperty("title", "Voluntario no modificado, problemas en persona");
                        }
                    } else {
                        jsonResponse.addProperty("error", 1);
                        jsonResponse.addProperty("title", "Voluntario no modificado, problemas en persona");
                    }
                } else {
                    jsonResponse.addProperty("error", 1);
                    jsonResponse.addProperty("title", "Voluntario no modificado, problemas en usuario");
                }
                break;
            case "eliminar":
                respuesta = daoUsuario.delete(idUsuario);

                System.out.println("resUsuario " + respuesta);
                if (respuesta) {
                    jsonResponse.addProperty("error", 0);
                    jsonResponse.addProperty("title", "");
                    jsonResponse.addProperty("message", "Voluntario eliminado exitosamente");
                } else {
                    jsonResponse.addProperty("error", 1);
                    jsonResponse.addProperty("title", "Voluntario no eliminado");
                }
                break;
            case "postular":
                int idVoluntarioEvaluacion = daoVoluntario.postularEvento(idVoluntario, idEvaluacionOrganizacionEvento);
                System.out.println("resPostulacion " + idVoluntarioEvaluacion);

                if (idVoluntarioEvaluacion > 0) {
                    DaoRespuesta daoRespuesta = new DaoRespuesta();

                    for (int i = 0; i < respuestas.length; i++) {
                        BeanPregunta beanPregunta = new BeanPregunta();
                        beanPregunta.setIdPregunta(Integer.parseInt(idPreguntas[i]));
                        BeanRespuesta beanRespuesta = new BeanRespuesta();
                        beanRespuesta.setTextoRespuesta(respuestas[i]);

                        BeanVoluntarioEvaluacion beanVoluntarioEvaluacion = new BeanVoluntarioEvaluacion();
                        beanVoluntarioEvaluacion.setIdVoluntarioEvaluacion(idVoluntarioEvaluacion);
                        beanRespuesta.setVoluntarioEvaluacion(beanVoluntarioEvaluacion);
                        beanRespuesta.setPregunta(beanPregunta);
                        respuesta = daoRespuesta.insert(beanRespuesta);
                        System.out.println("resRespuesta" + i + ": " + respuesta);
                    }

                    if (respuesta) {
                        jsonResponse.addProperty("error", 0);
                        jsonResponse.addProperty("title", "");
                        jsonResponse.addProperty("message", "Te has postulado al evento exitosamente");
                    } else {
                        jsonResponse.addProperty("error", 1);
                        jsonResponse.addProperty("title", "La postulación no se realizó, problemas en respuesta");
                    }
                } else {
                    jsonResponse.addProperty("error", 1);
                    jsonResponse.addProperty("title", "La postulación no se realizó, problemas al postularse");
                }
                break;
            case "cancelar":
                respuesta = daoVoluntario.cancelarPostulacion(idVoluntario, idEvaluacionOrganizacionEvento);

                System.out.println("resCancelacion " + respuesta);
                if (respuesta) {
                    jsonResponse.addProperty("error", 0);
                    jsonResponse.addProperty("title", "");
                    jsonResponse.addProperty("message", "Has cancelado la postulación al evento exitosamente");
                } else {
                    jsonResponse.addProperty("error", 1);
                    jsonResponse.addProperty("title", "La cancelación a la postulación no se realizó");
                }
                break;
            case "bloquear":
                respuesta = daoVoluntario.bloquearVoluntario(idVoluntario);

                System.out.println("resBloqueo" + respuesta);
                if (respuesta) {
                    jsonResponse.addProperty("error", 0);
                    jsonResponse.addProperty("title", "");
                    jsonResponse.addProperty("message", "Has bloqueado al voluntario");
                } else {
                    jsonResponse.addProperty("error", 1);
                    jsonResponse.addProperty("title", "El bloqueo del voluntario no se realizó");
                }
                break;
            case "aceptarRechazar":
                String resExitosa = "Voluntario aceptado al evento exitosamente";
                String resError = "Problema al aceptar el voluntario";
                if (idEstatusAceptadoRechazado == 3) {
                    resExitosa = "Voluntario rechazado al evento exitosamente";
                    resError = "Problema al rechazar el voluntario";
                }

                respuesta = daoVoluntario.aceptarRechazarVoluntario(idVoluntario, idEvaluacionOrganizacionEvento, idEstatusAceptadoRechazado);

                System.out.println("resPostulacion " + respuesta);
                if (respuesta) {
                    jsonResponse.addProperty("error", 0);
                    jsonResponse.addProperty("title", "");
                    jsonResponse.addProperty("message", resExitosa);
                } else {
                    jsonResponse.addProperty("error", 1);
                    jsonResponse.addProperty("title", resError);
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
        DaoVoluntario daoVoluntario = new DaoVoluntario();
        Utilidades utilidades = new Utilidades();
        List<BeanVoluntario> listaVoluntarios = new ArrayList<>();

        try {
            if (req.getParameter("consulta").equals("todosVoluntarios")) {
                listaVoluntarios = daoVoluntario.findAll();
            } else if (req.getParameter("consulta").equals("todos")) {
                listaVoluntarios = daoVoluntario.voluntariosPorOrganizacion(utilidades.numeroInt(req.getParameter("idOrganizacion")));
            } else if (req.getParameter("consulta").equals("pendientes")) {
                listaVoluntarios = daoVoluntario.voluntariosPorEstatus(utilidades.numeroInt(req.getParameter("idOrganizacion")), 1);
            } else if (req.getParameter("consulta").equals("aceptados")) {
                listaVoluntarios = daoVoluntario.voluntariosPorEstatus(utilidades.numeroInt(req.getParameter("idOrganizacion")), 2);
            } else if (req.getParameter("consulta").equals("rechazados")) {
                listaVoluntarios = daoVoluntario.voluntariosPorEstatus(utilidades.numeroInt(req.getParameter("idOrganizacion")), 3);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String json = gson.toJson(listaVoluntarios);
        resp.setContentType("text/json");
        resp.getWriter().write(json);
    }
}