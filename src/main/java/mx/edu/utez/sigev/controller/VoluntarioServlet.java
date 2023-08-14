package mx.edu.utez.sigev.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import mx.edu.utez.sigev.model.BeanPersona;
import mx.edu.utez.sigev.model.BeanRol;
import mx.edu.utez.sigev.model.BeanUsuario;
import mx.edu.utez.sigev.model.BeanVoluntario;
import mx.edu.utez.sigev.model.DAO.DaoPersona;
import mx.edu.utez.sigev.model.DAO.DaoUsuario;
import mx.edu.utez.sigev.model.DAO.DaoVoluntario;

import javax.servlet.RequestDispatcher;
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
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        int idPersona = Integer.parseInt(request.getParameter("idPersona"));
        int idVoluntario = Integer.parseInt(request.getParameter("idVoluntario"));

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
        Boolean respuesta;

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
                            jsonResponse.addProperty("error", 0);
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

        List<BeanVoluntario> listaVoluntarios = new ArrayList<>();

        try {
            listaVoluntarios = daoVoluntario.findAll();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String json = gson.toJson(listaVoluntarios);
        System.out.println(json);
        resp.setContentType("text/json");
        resp.getWriter().write(json);
    }
}