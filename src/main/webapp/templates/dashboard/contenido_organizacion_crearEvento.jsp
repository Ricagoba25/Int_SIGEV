<link href="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.css" rel="stylesheet">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<section class="content">
    <!-- Main content -->
    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4" style="margin-left: 100px">
        <div class=" pt-3 pb-2 mb-3 border-bottom">
            <h1 class="h2 mt-4 text-center">CREAR EVENTO</h1>
        </div>
        <!--Contenido-->

        <!-- Logo Imagen-->

        <!--Formulario-->
        <div id="formContainercrearEvento">
            <div class="container">
                <div class="container__formulario" style="margin-bottom: 200px">
                    <div class="container__formulario_contenido">
                        <form class="form-signin" action="../../ServletOrganizacion" method="post">
                            <!-- Primera fila-->

                            <div class="row mt-2">
                                <div class="col-xl-4">
                                    <label for="nombreEvento" class="form-label">Nombre Evento:</label>
                                    <input type="text" name="nombreEvento" class="form-control" id="nombreEvento" required>
                                </div>
                                <div class="col-xl-4">
                                    <label for="fecha" class="form-label">Fecha:</label>
                                    <input type="date" name="fecha" class="form-control" id="fecha" required>
                                </div>
                                <div class="col-xl-4 d-flex justify-content-center align-items-center">
                                    <button type="submit" name="accion" value="agregarOrganizacion"
                                            class="btn btn-primary"> Seleccionar Test
                                    </button>
                                </div>


                            </div>
                            <!-- Segunda Fila-->
                            <div class="row mt-2">
                                <div class="col-xl-4">
                                    <label for="calle" class="form-label">Calle:</label>
                                    <input type="text" name="calle" class="form-control" id="calle" required>
                                </div>
                                <div class="col-xl-3">
                                    <label for="noExterior" class="form-label">No Exterior:</label>
                                    <input type="text" name="noExterior" class="form-control" id="noExterior" required>
                                </div>
                                <div class="col-xl-3">
                                    <label for="noInterior" class="form-label">No Interior:</label>
                                    <input type="text" name="noInterior" class="form-control" id="noInterior" required>
                                </div>
                            </div>
                            <!-- Tercera linea-->
                            <div class="row mt-2">
                                <div class="col-xl-4">
                                    <label for="colonia" class="form-label">Colonia:</label>
                                    <input type="text" name="colonia" class="form-control" id="colonia" required>
                                </div>
                                <div class="col-xl-4">
                                    <label for="municipio" class="form-label">Municipio:</label>
                                    <input type="text" name="municipio" class="form-control" id="municipio" required>
                                </div>
                                <div class="col-xl-4">
                                    <label for="estado" class="form-label">Estado:</label>
                                    <input type="text" name="estado" class="form-control" id="estado" required>
                                </div>

                            </div>
                            <!-- Cuarta linea-->
                            <div class="row mt-2 mt-5">
                                <div class="input-group col-xl-12">
                                    <span class="input-group-text">Descripción:</span>
                                    <textarea class="form-control" aria-label="With textarea"></textarea>
                                </div>
                            </div>
                            <!-- Button -->
                            <div class="row mt- mt-3 justify-content-center">
                                <div class="col-xl-4">
                                    <button type="submit" name="accion" value="agregarOrganizacion"
                                            class="btn btn-primary btn-login mb-4 mt-4 ml-5"> Crear evento
                                    </button>
                                </div>

                            </div>
                        </form>
                    </div>

                </div>


    </main>
</section>


<script src="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.js"></script>