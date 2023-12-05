<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
  request.setAttribute("pageTitle", "Eventos Disponibles | SIGEV");
  request.setAttribute("url_parent", "./contenido_voluntario_disponibles.jsp");
%>
<!-- Incluye el template -->
<jsp:include page="../templates/dashboard/template_dashboard.jsp" />