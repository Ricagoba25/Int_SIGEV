package mx.edu.utez.sigev.controller;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mx.edu.utez.sigev.model.BeanUsuario;
import mx.edu.utez.sigev.model.DAO.DaoUsuario;
import mx.edu.utez.sigev.utils.EmailService;
@WebServlet(name = "restablecerContrasenia", value = "/reset-password")
public class restablecerContrasenia extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String correo = req.getParameter("correo"); // Obtener el correo electrónico del formulario

        // Crear una instancia de SendMail
        EmailService emailSender = new EmailService();

        System.out.println(correo);

        String correoPrueba = correo;
        DaoUsuario daoUsuario = new DaoUsuario();

        BeanUsuario usuario = (BeanUsuario) daoUsuario.findbyCorreo(correo);

        System.out.println("Contra " + usuario.getContrasena());


        // Llamar al método sendEmail con los parámetros necesarios
        String asunto = "Restablecimiento de contraseña";
        String mensaje = "Hola, tu contraseña es: " + usuario.getContrasena();
        //File archivoAdjunto = new File("ruta/al/archivo.pdf"); // Cambia por la ruta de tu archivo adjunto
        emailSender.sendEmail(correoPrueba, asunto, mensaje);


    }
}
