<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Sidebar -->
<div class="sidebar">
    <!-- Sidebar user panel (optional) -->
    <c:if test="${not empty sesion}">
        <div class="user-panel mt-3 pb-3 mb-3 d-flex">
            <div class="image">
                <img src="/assets/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image">
            </div>
            <div class="info">
                <c:if test="${tipoSesion == 'Administrador'}">
                    <a href="#" class="d-block"> ${sesion.getNombreCompleto()}</a>
                    <p class="mb-0 mt-0" style="color: #fff;">Eres <strong>Administrador</strong></p>
                </c:if>
                <c:if test="${tipoSesion == 'Organización'}">
                    <a href="#" class="d-block"> ${sesion.getNombreOrganizacion()}</a>
                    <p class="mb-0 mt-0" style="color: #fff;">Eres <strong>Organizacion</strong></p>
                </c:if>
                <c:if test="${tipoSesion == 'Voluntario'}">
                    <a href="#" class="d-block"> ${sesion.getPersona().getNombreCompleto()}</a>
                    <p class="mb-0 mt-0" style="color: #fff;">Eres <strong>Voluntario</strong></p>
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
    <!-- Menu dependiendo del tipo de usuario-->
    <nav class="mt-2">
        <c:if test="${not empty sesion}">
            <ul class="nav nav-pills nav-sidebar nav-child-indent flex-column">
                <c:if test="${tipoSesion == 'Administrador'}">
                    <li class="nav-item">
                        <a href="#" class="nav-link">
                            <i class="fa fa-building"></i>
                            <p>Organización </p>
                        </a>
                    </li>
                </c:if>

                <li class="nav-item">
                    <a href="#" class="nav-link">
                        <i class="fa fa-building"></i>
                        <p>Eventos </p>
                    </a>
                </li>


                <!-- User Voluntario-->
                <c:if test="${tipoSesion == 'Voluntario'}">
                    <li class="nav-item">
                        <a href="#" class="nav-link">
                            <i class="fa fa-building"></i>
                            <p>Notificaciones </p>
                        </a>
                    </li>
                </c:if>


                <c:if test="${tipoSesion == 'Administrador'}">
                    <li>
                        <a href="#" class="nav-link">
                            <i class="fa fa-users"></i>
                            <p>Voluntarios </p>
                        </a>
                    </li>
                    <li>
                        <a href="./usuarios.jsp" class="nav-link">
                            <i class="fa fa-user"></i>
                            <p>Usuarios </p>
                        </a>
                    <li>
                        <a href="#" class="nav-link">
                            <i class="fa fa-file"></i>
                            <p style="margin-left: 10px">Generar Reportes</p>
                        </a>
                    </li>
                </c:if>

            </ul>
        </c:if>

    </nav>

    <!-- /.sidebar-menu -->
</div>
<!-- /.sidebar -->