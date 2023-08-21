package mx.edu.utez.sigev.controller;

import com.google.gson.JsonObject;
import mx.edu.utez.sigev.model.*;
import mx.edu.utez.sigev.model.DAO.DaoOrganizacion;
import mx.edu.utez.sigev.model.DAO.DaoPersona;
import mx.edu.utez.sigev.model.DAO.DaoUsuario;
import mx.edu.utez.sigev.model.DAO.DaoVoluntario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;


@WebServlet(name = "SesionServlet", value = "/sesion-servlet")
public class SesionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Crear una respuesta en formato JSON
        JsonObject jsonResponse = new JsonObject();

        // Obtener los datos del formulario
        String email = request.getParameter("correo");
        String pass = request.getParameter("contrasenia");
        String accion = request.getParameter("accion");

        DaoUsuario daoUsuario = new DaoUsuario();

        switch (accion) {
            case "iniciarSesion":
                BeanUsuario usuarioSesion = (BeanUsuario) daoUsuario.iniciarSesion(email, pass);

                if (usuarioSesion.getIdUsuario() != 0) {
                    if (usuarioSesion.getEstatusUsuario() == 1) {

                        request.getSession().setAttribute("tipoSesion", usuarioSesion.getRol().getNombreRol());
                        request.getSession().setAttribute("usuario", usuarioSesion);

                        switch (usuarioSesion.getRol().getNombreRol()) {
                            case "Administrador":
                                DaoPersona daoPersona = new DaoPersona();
                                BeanPersona personaSesion = (BeanPersona) daoPersona.findOne(usuarioSesion.getIdUsuario());
                                request.getSession().setAttribute("sesion", personaSesion);
                                break;
                            case "Organización":
                                DaoOrganizacion daoOrganizacion = new DaoOrganizacion();
                                BeanOrganizacion organizacionSesion = (BeanOrganizacion) daoOrganizacion.findOne(usuarioSesion.getIdUsuario());
                                request.getSession().setAttribute("sesion", organizacionSesion);
                                break;
                            case "Voluntario":

                                DaoVoluntario daoVoluntario = new DaoVoluntario();
                                BeanVoluntario voluntarioSesion = (BeanVoluntario) daoVoluntario.findOne(usuarioSesion.getIdUsuario());
                                request.getSession().setAttribute("sesion", voluntarioSesion);

                                System.out.println(voluntarioSesion.getIdVoluntario());
                                System.out.println(voluntarioSesion.getPersona().getIdPersona());
                                System.out.println(voluntarioSesion.getPersona().getUsuario().getIdUsuario());

                                System.out.println(voluntarioSesion.getPersona().getPrimerApellido());


                                break;
                        }

                        jsonResponse.addProperty("error", 0);
                        jsonResponse.addProperty("title", "");
                        jsonResponse.addProperty("tipoUsuario",  usuarioSesion.getRol().getNombreRol());
                        jsonResponse.addProperty("message", "Inicio se sesión exitoso");

                    } else {
                        jsonResponse.addProperty("error", 1);
                        jsonResponse.addProperty("title", "Usuario eliminado");
                        jsonResponse.addProperty("message", "El usuario ha sido eliminado porfavor contacta a un administrador");
                    }
                } else {
                    jsonResponse.addProperty("error", 1);
                    jsonResponse.addProperty("title", "Usuario no encontrado");
                    jsonResponse.addProperty("message", "El usuario o la contraseña son incorrectos.");

                }
                break;
            case "cerrarSesion":
                request.getSession().removeAttribute("tipoSesion");
                request.getSession().removeAttribute("sesion");
                request.getSession().removeAttribute("usuario");
                HttpSession session = request.getSession(false);

                if (session != null) {
                    session.invalidate();
                }
                jsonResponse.addProperty("success", true);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        request.getSession().removeAttribute("sesion");
        resp.sendRedirect("index.jsp");
    }
}