package mx.edu.utez.sigev.controller;


import mx.edu.utez.sigev.model.Rol;
import mx.edu.utez.sigev.model.Usuario;
import mx.edu.utez.sigev.model.DAO.UsuarioDao;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet(name = "user", value = "/user-servlet")
public class userServlet extends HttpServlet {
    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Crear una respuesta en formato JSON
        JSONObject jsonResponse = new JSONObject();

        // Obtener los datos del formulario
        String email = request.getParameter("correo");
        String pass = request.getParameter("contrasenia");

        UsuarioDao dao = new UsuarioDao();
        Usuario usr = (Usuario) dao.findOne(email, pass);

        if (usr.getIdUsuario() != 0) {
            //Set rol
            Rol rol = new Rol();
            rol.setNombreRol(usr.getRol().getNombreRol());

            System.out.println("llegue aqui jeje");
            System.out.println(rol.getNombreRol());
            System.out.println(usr);

            request.getSession().setAttribute("tipoSesion", rol.getNombreRol());
            request.getSession().setAttribute("sesion",usr);

            String tipoSesion = rol.getNombreRol();

            if (tipoSesion != null) {
                if (tipoSesion.equals("Administrador")){
                    System.out.println("soy admin");
                    /*response.sendRedirect("/dashboard/index.jsp");*/
                } else if (tipoSesion.equals("Organización")) {
                    System.out.println("soy orga");
                    request.getRequestDispatcher("/dashboard/perfilOrganizacion.jsp").forward(request, response);
                    System.out.println("si mande a la pagina");
                }else {
                    System.out.println("soy volu");
                    request.getRequestDispatcher("/jsp/organizacion/perfilVoluntario.jsp").forward(request,response);
                }

            }

            jsonResponse.put("error", 0);
            jsonResponse.put("title", "");
            jsonResponse.put("message", "Inicio se sesión exitoso");

        } else {

            jsonResponse.put("error", 1);
            jsonResponse.put("title", "Usuario no encontrado.");
            jsonResponse.put("message", "El usuario o la contraseña son incorrectos.");

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
        /*resp.sendRedirect("index.jsp");*/
    }
}