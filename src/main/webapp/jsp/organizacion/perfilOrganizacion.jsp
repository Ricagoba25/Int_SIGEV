<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>SIGEV | Perfil Organización </title>

    <!-- Google Font: Source Sans Pro -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <!-- Font Awesome Icons -->
    <link rel="stylesheet" href="/assets/plugins/fontawesome-free/css/all.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/assets/css/adminlte.min.css">
    <link rel="stylesheet" href="/assets/css/dashboard.css">
    <link rel="stylesheet" href="/assets/css/styles.css.css">
    <link rel="stylesheet" href="/assets/css/style.css.css">

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

            <!-- Notifications Dropdown Menu -->
            <li class="nav-item dropdown">
                <a class="nav-link" data-toggle="dropdown" href="#">
                    <i class="far fa-bell"></i>
                    <span class="badge badge-warning navbar-badge">15</span>
                </a>
                <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
                    <span class="dropdown-header">15 Notifications</span>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item">
                        <i class="fas fa-envelope mr-2"></i> 4 new messages
                        <span class="float-right text-muted text-sm">3 mins</span>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item">
                        <i class="fas fa-users mr-2"></i> 8 friend requests
                        <span class="float-right text-muted text-sm">12 hours</span>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item">
                        <i class="fas fa-file mr-2"></i> 3 new reports
                        <span class="float-right text-muted text-sm">2 days</span>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item dropdown-footer">See All Notifications</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-widget="fullscreen" href="#" role="button">
                    <i class="fas fa-expand-arrows-alt"></i>
                </a>
            </li>
            <li class="nav-item">
                <a>
                    <button class="btn btn-info" type="button" href="#"> Mi Perfil</button>
                </a>
            </li>
            <li class="nav-item">
                <a>
                    <button class="btn btn-danger" id="btnCerrarSesion" style="margin-right: 20px; margin-left: 20px"> Cerrar Sesión </button>
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
        <div class="sidebar">
            <!-- Sidebar user panel (optional) -->
            <c:if test="${not empty sesion}">
                <div class="user-panel mt-3 pb-3 mb-3 d-flex">
                    <div class="image">
                        <img src="/assets/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image">
                    </div>
                    <div class="info">
                        <a href="#" class="d-block"> ${sesion.correo}</a>
                        <c:if test="${tipoSesion == 'Organización'}">
                            <p class="mb-0 mt-0" style="color: #fff;">Eres una <strong>Organización</strong></p>
                        </c:if>
                    </div>

                </div>
            </c:if>

            <!-- SidebarSearch Form -->
            <div class="form-inline">
                <div class="input-group" data-widget="sidebar-search">
                    <input class="form-control form-control-sidebar" type="search" placeholder="Search"
                           aria-label="Search">
                    <div class="input-group-append">
                        <button class="btn btn-sidebar">
                            <i class="fas fa-search fa-fw"></i>
                        </button>
                    </div>
                </div>
            </div>

            <!-- Sidebar Menu -->
            <nav class="mt-2">
                <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu"
                    data-accordion="false">
                    <!-- Add icons to the links using the .nav-icon class
                         with font-awesome or any other icon font library -->
                    <li class="nav-item menu-open">
                        <a href="#" class="nav-link active">
                            <i class="nav-icon fas fa-tachometer-alt"></i>
                            <p>
                                Menú Organización
                                <i class="right fas fa-angle-left"></i>
                            </p>
                        </a>
                        <ul class="nav nav-treeview">
                            <li class="nav-item">
                                <a class="nav-link2">
                                    <h5 style="margin-top: 25px; margin-bottom: 10px" ;>Solicitudes Voluntarios</h5>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="#" class="nav-link">
                                    <i class="far fa-solid fa-check"></i>
                                    <p style="margin-left: 10px">Aprobadas</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="#" class="nav-link">
                                    <i class="fa-regular fa-calendar-xmark"></i>
                                    <p style="margin-left: 10px">Pendientes</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link2">
                                    <h5 style="margin-top: 25px; margin-bottom: 10px" ;>Eventos</h5>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="#" class="nav-link">
                                    <i class="fa-solid fa-calendar-check"></i>
                                    <p style="margin-left: 10px">Publicados</p>
                                </a>
                            <li class="nav-item">
                                <a href="#" class="nav-link">
                                    <i class="fa-solid fa-calendar-plus"></i>
                                    <p style="margin-left: 10px">Crear Evento</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="#" class="nav-link">
                                    <i class="fa-regular fa-calendar-xmark"></i>
                                    <p style="margin-left: 10px">Pendientes Aprobación</p>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link2">
                                    <h5 style="margin-top: 25px; margin-bottom: 10px" ;>Tests</h5>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="#" class="nav-link">
                                    <i class="fa-solid fa-file-circle-plus"></i>
                                    <p style="margin-left: 10px">Crear Test</p>
                                </a>
                            <li class="nav-item">
                                <a href="#" class="nav-link">
                                    <i class="fa-solid fa-file-lines"></i>
                                    <p style="margin-left: 10px">Test Disponibles</p>
                                </a>
                            </li>
                        </ul>
                    </li>

                </ul>
            </nav>
            <!-- /.sidebar-menu -->
        </div>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- /.content-header -->

        <!-- Main content -->
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4" style="margin-left: 100px">
            <div class=" pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2 mt-4 text-center">PERFIL DE ${nombreGeneral}</h1>
            </div>
            <!--Contenido-->

            <!-- Logo Imagen-->
            <div class="m-auto mt-5 mb-4 text-center">
                <div>
                    <img src="/assets/img/Generic%20photo.jpg" alt="FotoPerfil" class="fotoPerfil rounded-circle">
                </div>
                <div>
                    <a href="#" class="text-center" data-bs-toggle="modal" data-bs-target="#exampleModal">Cambiar Logo</a>
                </div>
            </div>

            <!--Formulario-->
            <div class="container__formulario">
                <div class="container__formulario_contenido">
                    <!-- Primera fila-->
                    <form action="../../index.jsp" method="post">
                        <div class="row mt-2">
                            <div class="col-xl-4">
                                <label for="nombre" class="form-label">Nombre:</label>
                                <input type="text" value="${nombre}" name="nombre" class="form-control" id="nombre">
                            </div>
                            <div class="col-xl-4">
                                <label for="razonSocial" class="form-label">Razón Social*:</label>
                                <input type="text" class="form-control" value="${razonSocial}" name="razonSocial"
                                       id="razonSocial">
                            </div>
                            <div class="col-xl-4">
                                <label for="rfc" class="form-label">RFC*:</label>
                                <input type="text" value="${rfc}" name="rfc" class="form-control" id="rfc">
                            </div>
                        </div>
                        <!-- Segunda Fila-->
                        <div class="row mt-2">
                            <div class="col-xl-8">
                                <label for="direccion" class="form-label">Dirección:</label>
                                <input type="text" value="${direccion}" name="direccion" class="form-control"
                                       id="direccion">
                            </div>
                            <div class="col-xl-4">
                                <label for="telefono" class="form-label">Teléfono:</label>
                                <input type="text" value="${telefono}" name="telefono" class="form-control"
                                       id="telefono">
                            </div>
                        </div>
                        <!-- Tercera linea-->
                        <div class="row mt-2">
                            <div class="col-xl-8">
                                <label for="correo" class="form-label">Email*:</label>
                                <input type="text" value="${correo}" name="correo" class="form-control" id="correo">
                            </div>

                        </div>
                        <!-- Button -->
                        <div class="m-auto mt-5 text-center">
                            <div>
                                <button type="submit" class="btn btn-primary btn-login mb-4 mt-4"> Actualizar
                                    Información
                                </button>
                            </div>
                            <div>
                                <h6 class="text-center aste__marcados">Los elementos marcados con * no son
                                    actualizables</h6>
                            </div>
                        </div>
                    </form>
                </div>

            </div>


        </main>
        <!-- /.content -->



    </div>
    <!-- /.content-wrapper -->



    <!-- Main Footer -->
    <footer class="main-footer">
        <!-- To the right -->
        <div class="float-right d-none d-sm-inline">

        </div>


    </footer>
</div>
<!-- ./wrapper -->

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Selecciona tu Imagen</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">

                <div class="input-group mb-3 mt-3">
                    <input type="file" class="form-control" id="inputGroupFile02">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <button type="button" class="btn btn-primary">Guardar Imagen</button>
            </div>
        </div>
    </div>
</div>

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
                url: "/ServletCerrarSesion",
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
<!-- jQuery -->
<script src="/assets/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="/assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="/assets/js/adminlte.min.js"></script>
</body>
</html>
