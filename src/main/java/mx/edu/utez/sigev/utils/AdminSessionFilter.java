package mx.edu.utez.sigev.utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(urlPatterns = {


}) // This filter will be applied to all URLs


public class AdminSessionFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Get the HttpSession from the request
        HttpSession session = httpRequest.getSession(false);

        System.out.println("Ejecutando filter Route");
        System.out.println(session);

        if (session != null && session.getAttribute("tipoSesion") != null
                && session.getAttribute("tipoSesion").equals("Administrador")) {
            // El usuario tiene una sesión válida con el rol de administrador, permite el acceso.
            chain.doFilter(request, response);
        } else {
            // El usuario no tiene una sesión válida con el rol de administrador, bloquea el acceso.
            request.getRequestDispatcher("/acceso-denegado.jsp").forward(request, response);
        }


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
