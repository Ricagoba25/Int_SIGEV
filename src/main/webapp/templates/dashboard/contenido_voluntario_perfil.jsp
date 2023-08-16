<link href="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.css" rel="stylesheet">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<section class="content">
    <!-- Main content -->
    <c:if test="${not empty sesion}">
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4" style="margin-left: 100px">
            <div class=" pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2 mt-4 text-center">${sesion.getPersona().getNombreCompleto() } </h1>
            </div>
            <!--Contenido-->

            <!-- Logo Imagen-->
            <div class="m-auto mt-5 mb-4 text-center">
                <div>
                    <img src="/assets/img/Generic%20photo.jpg" alt="FotoPerfil" class="fotoPerfil rounded-circle">
                </div>
                <div>
                    <a href="#" class="text-center" data-bs-toggle="modal" data-bs-target="#exampleModal">Cambiar
                        Logo</a>
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
                                <input type="text" value="${sesion.getPersona().getNombrePersona()}" name="nombre" class="form-control" id="nombre">
                            </div>
                            <div class="col-xl-4">
                                <label for="primerApellido" class="form-label">Apellido Paterno:</label>
                                <input type="text" class="form-control" value="${sesion.getPersona().getPrimerApellido()}" name="primerApellido"
                                       id="primerApellido">
                            </div>
                            <div class="col-xl-4">
                                <label for="segundoApellido" class="form-label">Apellido Materno:</label>
                                <input type="text" value="${sesion.getPersona().getSegundoApellido()}" name="segundoApellido"
                                       class="form-control" id="segundoApellido">
                            </div>
                        </div>
                        <!-- Segunda Fila-->
                        <div class="row mt-2">
                            <div class="col-xl-4">
                                <label for="correo" class="form-label">Correo*:</label>
                                <input type="text" value="${usuario.getCorreo()}" name="correo" class="form-control"
                                       id="correo">
                            </div>
                            <div class="col-xl-4">
                                <label for="telefono" class="form-label">Teléfono:</label>
                                <input type="text" value="${usuario.getTelefono()}" name="telefono" class="form-control"
                                       id="telefono">
                            </div>
                            <div class="col-xl-4">
                                <label for="curp" class="form-label">Curp*:</label>
                                <input type="text" value="${sesion.getCurp()}" name="curp" class="form-control"
                                       id="curp">
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
    </c:if>
</section>


<script src="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.js"></script>