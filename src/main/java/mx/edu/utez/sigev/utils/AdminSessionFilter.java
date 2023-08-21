package mx.edu.utez.sigev.utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


@WebFilter(urlPatterns = {"/dashboard/*", "/dashboard/template_organizacion_*", "/dashboard/template_voluntario_*"})


public class AdminSessionFilter implements Filter {

    private final Pattern adminPattern = Pattern.compile("^/dashboard/template_admin_.*");
    private final Pattern organizacionPattern = Pattern.compile("^/dashboard/template_organizacion_.*");
    private final Pattern voluntarioPattern = Pattern.compile("^/dashboard/template_voluntario_.*");


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
        String userType = getUserTypeFromSession(request);


        // Obtener la ruta de la solicitud
        String requestURI = ((HttpServletRequest) request).getRequestURI();


        System.out.println("Soy: " + userType);
        if (userType == null) {
            //.getWriter().write("Acceso denegado. Debes iniciar sesión.");
            request.getRequestDispatcher("/acceso-denegado.jsp").forward(request, response);

            return;
        }

        if ("Organización".equals(userType) && (matchesPattern(requestURI, adminPattern) || matchesPattern(requestURI, voluntarioPattern))) {

            response.getWriter().write("Acceso denegado. Para organización no tienes permitido ver este recurso.");
            return;
        }
        if ("Voluntario".equals(userType) && (matchesPattern(requestURI, adminPattern) || matchesPattern(requestURI, organizacionPattern))) {

            response.getWriter().write("Acceso denegado. Para Voluntario no tienes permitido ver este recurso.");
            return;

        }

        chain.doFilter(request, response);

    }

    // Método para obtener el tipo de usuario desde la sesión (debes implementar este método)
    private String getUserTypeFromSession(ServletRequest request) {
        // Implementa la lógica para obtener el tipo de usuario (administrador, voluntario, organización) desde la sesión
        // Puede usar request.getSession().getAttribute("userType") u otro mecanismo similar
        // y devolver el tipo de usuario como una cadena
        HttpSession session = ((HttpServletRequest) request).getSession(false); // No crea una nueva sesión si no existe

        if (session != null) {
            return (String) session.getAttribute("tipoSesion");
        }
        return null;
    }

    // Método para verificar si una cadena coincide con un patrón regex
    private boolean matchesPattern(String input, Pattern pattern) {
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
