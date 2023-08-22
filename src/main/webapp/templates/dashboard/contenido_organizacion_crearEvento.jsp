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
                        <form id="form_crear_evento">
                            <!-- Primera fila-->

                            <input type="hidden" id="idOrganizacion" value="${sesion.getIdOrganizacion()}">

                            <div class="row mt-2">
                                <div class="col-xl-4">
                                    <label for="nombreEvento" class="form-label">Nombre Evento:</label>
                                    <input type="text" name="nombreEvento" class="form-control" id="nombreEvento"
                                           name="nombreEvento" required>
                                </div>
                                <div class="col-xl-4">
                                    <label for="fecha" class="form-label">Fecha:</label>
                                    <input type="date" name="fecha" class="form-control" id="fecha" name="fecha" required min="2023-08-22">
                                </div>

                                <!--
                                <div class="col-xl-4 d-flex justify-content-center align-items-center">
                                    <button class="btn btn-success" id="modalSeleccionarTest"> Seleccionar Evaluación
                                    </button>
                                </div>
                                -->


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
                                    <label for="stateSelect" class="form-label">Estado:</label>
                                    <select class="form-control" id="stateSelect" name="estado"></select>
                                </div>

                            </div>
                            <!-- Cuarta linea-->
                            <div class="row mt-2 mt-5">
                                <div class="input-group col-xl-12">
                                    <span class="input-group-text">Descripción:</span>
                                    <textarea class="form-control" aria-label="With textarea"
                                              name="descripcion" id="descripcion"></textarea>
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
            </div>
        </div>
    </main>

</section>


<script>
    $(document).ready(function () {

        obtenerEstados();

        $("#form_crear_evento").validate({
            errorClass: "is-invalid",
            validClass: "is-valid",
            rules: {
                nombreEvento: {
                    required: true
                },
                fecha: {
                    required: true
                },
                calle: {
                    required: true
                },
                noExterior: {
                    required: true,
                },
                noInterior: {
                    required: true,
                },
                colonia: {
                    required: true,
                },
                municipio: {
                    required: true,
                },
                estado: {
                    required: true,
                },
                descripcion: {
                    required: true,
                },

            },
            messages: {
                nombreEvento: {
                    required: "El Nombre es requerido.",
                },
                fecha: {
                    required: "La fecha es requerida.",
                },
                calle: {
                    required: "La calle materno es requerido.",
                },
                noExterior: {
                    required: "Este campo es requerido.",
                },
                noInterior: {
                    required: "Este campo es requerido.",
                },
                colonia: {
                    required: "La colonia es requerida.",
                },
                municipio: {
                    required: "El municipio es requerido.",
                },
                estado: {
                    required: "El estado es requerido.",
                },
                descripcion: {
                    required: "La descripción es requerida.",
                },

            },
            submitHandler: function (form) {
                crearEvento();
            }
        })


        // Abrir el modal cuando se haga clic en el botón "Cerrar sesión"
        $("#modalSeleccionarTest").click(function () {
            $("#modalElegir").modal("show");
        });
    });

    function crearEvento() {

        let formData = {
            accion: 'registrar',
            nombreEvento: $("#nombreEvento").val(),
            fecha: $("#fecha").val(),
            calle: $("#calle").val(),
            noExterior: $("#noExterior").val(),
            noInterior: $("#noInterior").val(),
            colonia: $("#colonia").val(),
            municipio: $("#municipio").val(),
            idEstado: $("#stateSelect").val(),
            descripcion: $("#descripcion").val(),
            idOrganizacion: $("#idOrganizacion").val(),
            estatusEvento: 1
        }


        $.ajax({
            type: "POST",
            url: "/evento",
            data: formData,
            success: function (response) {
                loading = false;
                // Procesar la respuesta del servlet si es necesario
                console.log("Respuesta del servidor:", response);

                if (response.error) {
                    Swal.fire({
                        position: 'center',
                        icon: 'error',
                        title: 'Error',
                        text: response.title,
                        showConfirmButton: false,
                        timer: 2500
                    })

                } else {
                    // si la respuesta es exitosa

                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Creación exitosa',
                        text: "Evento creado correctamente.",
                        showConfirmButton: false,
                        timer: 2500
                    }).then(() => {
                        window.location = "./template_organizacion_eventosPublicados.jsp";
                    })


                }
            },
            error: function (error) {
                loading = false;
                console.error("Error en la petición AJAX:", error);
            }
        });

    }

    let obtenerEstados = () => {
        $.ajax({
            url: '/estado',
            method: 'GET',
            dataType: 'json',
            success: function (data) {

                // Obtener el elemento select
                let selectElement = $('#stateSelect');
                selectElement.append($('<option>', {
                    value: "",
                    text: "Selecciona un estado"
                }));

                // Recorrer los datos y agregar opciones al select
                $.each(data, function (index, state) {
                    selectElement.append($('<option>', {
                        value: state.idEstado,
                        text: state.nombre
                    }));
                });
            },
            error: function () {
                console.error('Error al cargar la lista de estados.');
            }
        });
    }


</script>


<div id="modalElegir" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="miModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="miModalLabel">Elegir Evaluación</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!--Contenido del Modal -->
                <section class="content">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-body">
                                        <table id="example1" class="table table-bordered table-striped">
                                            <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Nombre Evaluación</th>
                                                <th>Acciones</th>
                                            </tr>
                                            </thead>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button id="guardarCambios" type="button" class="btn btn-primary">Aceptar</button>
            </div>
        </div>
    </div>
</div>


<style>

    #form_crear_evento div div .is-invalid {
        font-size: 12px;
        color: red;
    }
</style>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.min.js"></script>