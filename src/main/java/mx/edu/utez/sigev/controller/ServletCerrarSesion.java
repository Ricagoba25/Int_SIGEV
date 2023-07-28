package mx.edu.utez.sigev.controller;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServletCerrarSesion", value = "/ServletCerrarSesion")
public class ServletCerrarSesion  extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session != null){
            session.invalidate();
        }

        System.out.println("llego aqui");
        resp.setContentType("application/json");
        resp.getWriter().write("{\"success\": true}");
    }
}
