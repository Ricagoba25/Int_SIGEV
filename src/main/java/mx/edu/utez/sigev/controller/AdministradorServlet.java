package mx.edu.utez.sigev.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import mx.edu.utez.sigev.model.BeanPersona;
import mx.edu.utez.sigev.model.BeanRol;
import mx.edu.utez.sigev.model.BeanUsuario;
import mx.edu.utez.sigev.model.DAO.DaoPersona;
import mx.edu.utez.sigev.model.DAO.DaoUsuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "AdministradorServlet", value = "/administrador")
public class AdministradorServlet extends HttpServlet {
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
        String primerapellido = request.getParameter("primerapellido");
        String segundoapellido = request.getParameter("segundoapellido");

        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        int idPersona = Integer.parseInt(request.getParameter("idPersona"));

        BeanUsuario usuario = new BeanUsuario();
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);
        usuario.setTelefono(telefono);
        usuario.setRol(new BeanRol(1));

        BeanPersona persona = new BeanPersona();
        persona.setNombrePersona(nombrePersona);
        persona.setPrimerApellido(primerapellido);
        persona.setSegundoApellido(segundoapellido);

        DaoUsuario daoUsuario = new DaoUsuario();
        DaoPersona daoPersona = new DaoPersona();
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
                        jsonResponse.addProperty("error", 0);
                        jsonResponse.addProperty("title", "");
                        jsonResponse.addProperty("message", "Administrador registrado exitosamente");
                    } else {
                        jsonResponse.addProperty("error", 1);
                        jsonResponse.addProperty("title", "Administrador no registrado, problemas en persona");
                    }
                } else {
                    jsonResponse.addProperty("error", 1);
                    jsonResponse.addProperty("title", "Administrador no registrado, problemas en usuario");
                }
                break;
            case "modificar":
                usuario.setIdUsuario(idUsuario);
                persona.setIdPersona(idPersona);

                respuesta = daoUsuario.update(usuario.getIdUsuario(), usuario);

                System.out.println("resUsuario " + respuesta);
                if (respuesta) {
                    respuesta = daoPersona.update(persona.getIdPersona(), persona);
                    System.out.println("resPersona " + respuesta);
                    if (respuesta) {
                        jsonResponse.addProperty("error", 0);
                        jsonResponse.addProperty("title", "");
                        jsonResponse.addProperty("message", "Administrador modificado exitosamente");
                    } else {
                        jsonResponse.addProperty("error", 1);
                        jsonResponse.addProperty("title", "Administrador no modificado, problemas en persona");
                    }
                } else {
                    jsonResponse.addProperty("error", 1);
                    jsonResponse.addProperty("title", "Administrador no modificado, problemas en usuario");
                }
                break;
            case "eliminar":
                respuesta = daoUsuario.delete(idUsuario);

                System.out.println("resUsuario " + respuesta);
                if (respuesta) {
                    jsonResponse.addProperty("error", 0);
                    jsonResponse.addProperty("title", "");
                    jsonResponse.addProperty("message", "Administrador eliminado exitosamente");
                } else {
                    jsonResponse.addProperty("error", 1);
                    jsonResponse.addProperty("title", "Administrador no eliminado");
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
        DaoPersona daoPersona = new DaoPersona();

        List<BeanPersona> listaAdmins = new ArrayList<>();

        try {
            listaAdmins = daoPersona.findAll();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String json = gson.toJson(listaAdmins);

        resp.setContentType("text/json");
        resp.getWriter().write(json);
    }
}