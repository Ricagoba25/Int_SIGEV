package mx.edu.utez.sigev.controller;


import com.google.gson.JsonObject;
import mx.edu.utez.sigev.model.BeanRol;
import mx.edu.utez.sigev.model.BeanUsuario;
import mx.edu.utez.sigev.model.DAO.DaoUsuario;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "user", value = "/user-servlet")
public class loginServletDeprecated extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Crear una respuesta en formato JSON
        JsonObject jsonResponse = new JsonObject();

        // Obtener los datos del formulario
        String email = request.getParameter("correo");
        String pass = request.getParameter("contrasenia");

        DaoUsuario dao = new DaoUsuario();
        BeanUsuario usr = (BeanUsuario) dao.iniciarSesion(email, pass);

        if (usr.getIdUsuario() != 0) {
            //Set rol
            BeanRol beanRol = new BeanRol();
            beanRol.setNombreRol(usr.getRol().getNombreRol());
            request.getSession().setAttribute("tipoSesion", beanRol.getNombreRol());
            request.getSession().setAttribute("sesion", usr);


            jsonResponse.addProperty("error", 0);
            jsonResponse.addProperty("title", "");
            jsonResponse.addProperty("message", "Inicio se sesión exitoso");


            jsonResponse.addProperty("tipoSesion", beanRol.getNombreRol());

        } else {

            jsonResponse.addProperty("error", 1);
            jsonResponse.addProperty("title", "Usuario no encontrado.");
            jsonResponse.addProperty("message", "El usuario o la contraseña son incorrectos.");

        }


        // Establecer el tipo de contenido de la respuesta a JSON

        response.setContentType("application/json");
       /* jsonResponse.put("status", "success");
        jsonResponse.put("message", "Inicio de sesión exitoso"); */

        // Enviar la respuesta al cliente
        response.getWriter().write(jsonResponse.toString());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Entra al get");
        resp.sendRedirect("index.jsp");
    }


}