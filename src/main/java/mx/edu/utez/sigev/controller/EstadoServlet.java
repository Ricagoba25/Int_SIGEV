package mx.edu.utez.sigev.controller;

import com.google.gson.Gson;
import mx.edu.utez.sigev.model.BeanEstado;
import mx.edu.utez.sigev.model.DAO.DaoEstado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "EstadoServlet", value = "/estado")
public class EstadoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<BeanEstado> listaEstados = new ArrayList<>();
        try {
            DaoEstado daoEstado = new DaoEstado();
            listaEstados = daoEstado.findAll();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String json = gson.toJson(listaEstados);

        resp.setContentType("text/json");
        resp.getWriter().write(json);

    }
}