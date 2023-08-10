package mx.edu.utez.sigev.controller;

import com.google.gson.Gson;
import mx.edu.utez.sigev.model.BeanEvento;
import mx.edu.utez.sigev.model.BeanPersona;
import mx.edu.utez.sigev.model.DAO.DaoEvento;
import mx.edu.utez.sigev.model.DAO.DaoPersona;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EventoServlet", value = "/evento")
public class eventoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DaoEvento daoEvento = new DaoEvento();

        List<BeanEvento> listaEventos = new ArrayList<>();

        try {
            listaEventos = daoEvento.findAll();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String json = gson.toJson(listaEventos);
        System.out.println(json);
        resp.setContentType("text/json");
        resp.getWriter().write(json);
    }
}
