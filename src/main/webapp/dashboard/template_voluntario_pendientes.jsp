<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
  request.setAttribute("pageTitle", "Eventos Pendientes | SIGEV");
  request.setAttribute("url_parent", "./contenido_voluntario_pendientes.jsp");
%>
<!-- Incluye el template -->
<jsp:include page="../templates/dashboard/template_dashboard.jsp" />