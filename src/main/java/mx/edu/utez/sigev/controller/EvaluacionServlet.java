package mx.edu.utez.sigev.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import mx.edu.utez.sigev.model.BeanEvaluacion;
import mx.edu.utez.sigev.model.BeanOrganizacion;
import mx.edu.utez.sigev.model.BeanPregunta;
import mx.edu.utez.sigev.model.DAO.DaoEvaluacion;
import mx.edu.utez.sigev.model.DAO.DaoPregunta;
import mx.edu.utez.sigev.utils.Utilidades;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = " EvaluacionServlet", value = "/evaluacion")
public class EvaluacionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Utilidades utilidades = new Utilidades();
        // Crear una respuesta en formato JSON
        JsonObject jsonResponse = new JsonObject();

        // Obtener los datos del formulario
        String accion = request.getParameter("accion");

        String nombreEvaluacion = request.getParameter("nombreEvaluacion");
        int idOrganizacion = utilidades.numeroInt(request.getParameter("idOrganizacion"));
        int idEvaluacion = utilidades.numeroInt(request.getParameter("idEvaluacion"));
        int estatusEvaluacion = utilidades.numeroInt(request.getParameter("estatusEvaluacion"));

        String[] preguntas = request.getParameterValues("preguntas[]");

        BeanOrganizacion organizacion = new BeanOrganizacion(idOrganizacion);
        BeanEvaluacion evaluacion = new BeanEvaluacion();
        evaluacion.setNombreEvaluacion(nombreEvaluacion);
        evaluacion.setEstatusEvaluacion(estatusEvaluacion);
        evaluacion.setOrganizacion(organizacion);

        BeanPregunta pregunta = new BeanPregunta();

        DaoEvaluacion daoEvaluacion = new DaoEvaluacion();
        DaoPregunta daoPregunta = new DaoPregunta();
        boolean respuesta = false;


        switch (accion) {
            case "registrar":
                idEvaluacion = daoEvaluacion.registrar(evaluacion);


                System.out.println("resEvaluacion " + idEvaluacion);
                if (idEvaluacion > 0) {
                    evaluacion.setIdEvaluacion(idEvaluacion);
                    //Registrar Pregunta

                    for (int i = 0; i < preguntas.length; i++) {
                        pregunta.setEvaluacion(evaluacion);
                        respuesta = daoPregunta.insert(pregunta);
                        System.out.println("resPregunta " + respuesta);
                    }

                    if (respuesta) {
                        jsonResponse.addProperty("error", 0);
                        jsonResponse.addProperty("title", "");
                        jsonResponse.addProperty("message", "Evaluación registrada exitosamente");
                    } else {
                        jsonResponse.addProperty("error", 1);
                        jsonResponse.addProperty("title", "Evaluación no registrada, problemas al registrar pregunta");
                    }
                } else {
                    jsonResponse.addProperty("error", 1);
                    jsonResponse.addProperty("title", "Evaluación no registrada, problemas en evaluacion");
                }
                break;
            case "modificar":
                evaluacion.setIdEvaluacion(idEvaluacion);

                respuesta = daoEvaluacion.update(evaluacion.getIdEvaluacion(), evaluacion);

                System.out.println("resevaluacion " + respuesta);
                if (respuesta) {
                    respuesta = daoPregunta.update(pregunta.getIdPregunta(), pregunta);
                    System.out.println("resEvento " + respuesta);
                    if (respuesta) {
                        jsonResponse.addProperty("error", 0);
                        jsonResponse.addProperty("title", "");
                        jsonResponse.addProperty("message", "Evaluación modificada exitosamente");
                    } else {
                        jsonResponse.addProperty("error", 1);
                        jsonResponse.addProperty("title", "Evaluación no modificada, problemas en pregunta");
                    }
                } else {
                    jsonResponse.addProperty("error", 1);
                    jsonResponse.addProperty("title", "Evaluación no modificada, problemas en Evaluación");
                }
                break;
            case "changeStatus":
                respuesta = daoEvaluacion.changeStatus(idEvaluacion, estatusEvaluacion);

                System.out.println("resEvaluacion " + respuesta);
                if (respuesta) {
                    jsonResponse.addProperty("error", 0);
                    jsonResponse.addProperty("title", "");
                    jsonResponse.addProperty("message", "Estatus de la evaluación modificado exitosamente");
                } else {
                    jsonResponse.addProperty("error", 1);
                    jsonResponse.addProperty("title", "El estatus de la evaluación  no se cambio");
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
        DaoEvaluacion daoEvaluacion = new DaoEvaluacion();
        Utilidades utilidades = new Utilidades();
        List<BeanEvaluacion> listaEvaluaciones = new ArrayList<>();
        try {
            if (req.getParameter("consulta").equals("evaluacionesActivas")) {
                listaEvaluaciones = daoEvaluacion.evaluacionesActivas(utilidades.numeroInt(req.getParameter("idOrganizacion")));
            } else if (req.getParameter("consulta").equals("evaluacionesPorOrganizacion")) {
                listaEvaluaciones = daoEvaluacion.evaluacionesPorOrganizacion(utilidades.numeroInt(req.getParameter("idOrganizacion")));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String json = gson.toJson(listaEvaluaciones);
        resp.setContentType("text/json");
        resp.getWriter().write(json);
    }
}
