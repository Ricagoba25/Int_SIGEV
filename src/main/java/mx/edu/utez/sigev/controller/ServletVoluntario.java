package mx.edu.utez.sigev.controller;

import mx.edu.utez.sigev.model.BeanPersona;
import mx.edu.utez.sigev.model.BeanUsuario;
import mx.edu.utez.sigev.model.BeanVoluntario;
import mx.edu.utez.sigev.model.DAO.DaoPersona;
import mx.edu.utez.sigev.model.DAO.DaoUsuario;
import mx.edu.utez.sigev.model.DAO.DaoVoluntario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name= "ServletVoluntario", urlPatterns = "/ServletVoluntario")
public class ServletVoluntario extends HttpServlet {

    DaoVoluntario daoVoluntario = new DaoVoluntario();
    DaoUsuario daoUsuario = new DaoUsuario();
    DaoPersona daoPersona = new DaoPersona();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        switch (accion){
            case "agregarVoluntario":

                String correoS = req.getParameter("correo");
                String nombrePersonaS = req.getParameter("nombrePersona");
                String primerapellidoS = req.getParameter("primerapellido");
                String segundoapellidoS = req.getParameter("segundoapellido");
                String telefonoS = req.getParameter("telefono");
                String curpS = req.getParameter("curp");
                String contrasenaS = req.getParameter("contrasena");
                System.out.println("agrego voluntario");


                BeanPersona persona = new BeanPersona();
                persona.setNombrePersona(nombrePersonaS);
                persona.setPrimerApellido(primerapellidoS);
                persona.setSegundoApellido(segundoapellidoS);
                System.out.println("seteo persona");


                BeanUsuario usuario = new BeanUsuario();
                usuario.setCorreo(correoS);
                usuario.setContrasena(contrasenaS);
                usuario.setTelefono(telefonoS);
                System.out.println("seteo usuario");


                BeanVoluntario voluntario = new BeanVoluntario();
                voluntario.setCurp(curpS);
                System.out.println("seto voluntario");



                DaoVoluntario daoVoluntario = new DaoVoluntario();

                daoVoluntario.insert(voluntario);

                break;
        }
    }
}
