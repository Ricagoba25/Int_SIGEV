<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><%= request.getAttribute("pageTitle") %>
    </title>
    <!-- Google Font: Source Sans Pro -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <!-- Font Awesome Icons -->
    <link rel="stylesheet" href="../../assets/plugins/fontawesome-free/css/all.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="../../assets/css/adminlte.min.css">
    <link rel="stylesheet" href="../../assets/css/dashboard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <!-- Bootstrap JS -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>


<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <!-- Navbar -->
    <nav class="main-header navbar navbar-expand navbar-white navbar-light">
        <!-- Left navbar links -->
        <ul class="navbar-nav mai-menu">
            <li class="nav-item">
                <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
            </li>
        </ul>

        <!-- Right navbar links -->
        <ul class="navbar-nav ml-auto">
            <!-- Navbar Search -->
            <li class="nav-item">
                <div class="navbar-search-block">
                    <form class="form-inline">
                        <div class="input-group input-group-sm">
                            <div class="input-group-append">
                                <button class="btn btn-navbar" type="submit">
                                    <i class="fas fa-search"></i>
                                </button>

                            </div>
                        </div>
                    </form>
                </div>
            </li>
            <!-- Perfil voluntario-->
            <c:if test="${tipoSesion == 'Voluntario'}">
                <li class="nav-item">
                    <a href="./template_voluntario_perfil.jsp">
                        <button class="btn btn-info"> Mi Perfil</button>
                    </a>
                </li>
            </c:if>
            <!-- Perfil Organización-->
            <c:if test="${tipoSesion == 'Organización'}">
                <li class="nav-item">
                    <a href="./template_organizacion_perfil.jsp">
                        <button class="btn btn-info"> Mi Perfil</button>
                    </a>
                </li>
            </c:if>
            <!-- Perfil Administrador-->
            <c:if test="${tipoSesion == 'Administrador'}">
                <li class="nav-item">
                    <a href="./template_admin_perfil.jsp">
                        <button class="btn btn-info"> Mi Perfil</button>
                    </a>
                </li>
            </c:if>


            <li class="nav-item">
                <a>
                    <button class="btn btn-danger" id="btnCerrarSesion" style="margin-right: 20px; margin-left: 20px">
                        Cerrar Sesión
                    </button>
                </a>
            </li>
        </ul>
    </nav>
    <!-- /.navbar -->

    <!-- Main Sidebar Container -->
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <!-- Brand Logo -->

        <a href="index.jsp" class="brand-link text-center">
            <img src="/assets/img/Logo%20SIGEV.JPG" class="img-circle elevation-2" width="120px" alt="User Image">
        </a>

        <!-- Sidebar -->
        <jsp:include page="/templates/sidebar.jsp"/>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <jsp:include page="${url_parent}"/>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Control sidebar content goes here -->
        <div class="p-3">
            <h5>Title</h5>
            <p>Sidebar content</p>
        </div>
    </aside>
    <!-- /.control-sidebar -->

    <!-- Main Footer -->
    <footer class="main-footer">
        <br>
    </footer>
</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->
<div class="modal" tabindex="-1" role="dialog" id="modalConfirmacionCerrarSesion">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">¿Estás seguro de cerrar sesión?</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button type="button" class="btn btn-danger" id="btnConfirmarCerrarSesion">Cerrar sesión</button>
            </div>
        </div>
    </div>
</div>

<!-- Script para manejar el evento del botón de "Cerrar sesión" -->
<script>
    $(document).ready(function () {
        // Abrir el modal cuando se haga clic en el botón "Cerrar sesión"
        $("#btnCerrarSesion").click(function () {
            $("#modalConfirmacionCerrarSesion").modal("show");
        });

        // Cerrar la sesión cuando se confirme en el modal
        $("#btnConfirmarCerrarSesion").click(function () {
            // Realizar una solicitud AJAX para cerrar la sesión en el servidor
            $.ajax({
                type: "POST",
                url: "/sesion-servlet",
                data: {
                    accion: 'cerrarSesion'
                },
                success: function (resp) {
                    console.log("si funciono");
                    // Redireccionar al usuario a la página de inicio de sesión
                    window.location.href = "/index.jsp";
                },
                error: function (error) {
                    console.error("Error al cerrar sesión:", error);
                    // Manejar el error si es necesario
                }
            });
        });
    });
</script>

</body>
</html>