<link href="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.css" rel="stylesheet">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="content-header">
    <div class="container-fluid">
        <div class="row mb-2">
            <div class="col-sm-6">
                <h1>Nuevos Voluntarios</h1>
            </div>
        </div>
    </div>
</section>

<section class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <input type="hidden" id="idOrganizacion" value="${sesion.getIdOrganizacion()}">
                    <div class="card-body">
                        <table id="example1" class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre voluntario</th>
                                <th>Apellido paterno</th>
                                <th>Apellido materno</th>
                                <th>Nombre evento</th>
                                <th>CURP</th>
                                <th>Correo</th>
                                <th>Teléfono</th>
                                <th>Estatus</th>
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


<script>
    $(document).ready(function () {
        const URL_API = "http://localhost:8080/"

        let idOrganizacion = $("#idOrganizacion").val()
        $('#example1').DataTable({
            "language": {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
            ajax:
                {
                    url: URL_API + 'voluntario?consulta=todos&idOrganizacion=' + idOrganizacion,
                    dataSrc: ''
                },
            columns: [
                {"data": "voluntario.idVoluntario"},
                {"data": "voluntario.persona.nombrePersona"},
                {"data": "voluntario.persona.primerApellido"},
                {"data": "voluntario.persona.segundoApellido"},
                {"data": "evaluacionOrganizacionEvento.evento.nombreEvento"},
                {"data": "voluntario.curp"},
                {"data": "voluntario.persona.usuario.correo"},
                {"data": "voluntario.persona.usuario.telefono"},
                // {"data": "voluntario.estatusVoluntarioEvaluacion"},
                {
                    data: null,
                    render: function (data, type, row) {
                        let estatus = "Rechazado";
                        if (data.estatusVoluntarioEvaluacion == 1) {
                            estatus = "Pendiente";
                        }
                        if (data.estatusVoluntarioEvaluacion == 2) {
                            estatus = "Aceptado";
                        }
                        return estatus;
                    }
                },
                {
                    // Añadir los botones de acciones "Editar" y "Borrar"
                    data: null,
                    render: function (data, type, row) {
                        // El contenido de esta función se ejecutará para cada celda de esta columna
                        // Utilizamos data para acceder a los datos de la fila actual

                        let aceptarBtn = '<a href="#" title="Aceptar" onclick=\'aceptar(' + JSON.stringify(data) + ')\'>  <i class="fa-solid fa-check"></i> Aceptar</a> &nbsp;';
                        let bloquearBtn = '<a href="#" title="Bloquear" onclick=\'cancelar(' + JSON.stringify(data) + ')\'>  <i class="fa-solid fa-ban"></i> Rechazar</a> &nbsp;';
                        let verRespustas = '<a href="#" id="editarBtn" onclick=\'verDatos(' + JSON.stringify(data) + ')\'> <i class="fa fa-eye"></i> Ver Respuestas <br></a>';

                        // Devolvemos los botones como una cadena HTML
                        return aceptarBtn + bloquearBtn + verRespustas;
                    }
                }

            ]

        });


    });

    function aceptar(data) {
        // Abrir el modal de confirmación
        $('#modalAceptar').modal('show');

        // Agregar un evento al botón de confirmación dentro del modal
        $('#confirmarAceptar').click(function () {
            // console.log('Editar usuario con ID:', id);

            console.log(data)


            $.ajax({
                type: "POST",
                url: "/voluntario",
                data: {
                    accion: "aceptarRechazar",
                    idVoluntario: data.voluntario.idVoluntario,
                    idEvaluacionOrganizacionEvento: data.evaluacionOrganizacionEvento.idEvaluacionOrganizacionEvento,
                    idEstatusAceptadoRechazado: 2
                },
                success: function (response) {
                    // Procesar la respuesta del servlet si es necesario
                    console.log("Respuesta del servidor:", response);
                    if (response.error) {
                        Swal.fire({
                            position: 'center',
                            icon: 'error',
                            title: 'Error',
                            text: "Tenemos algunos errores.",
                            showConfirmButton: false,
                            timer: 1500
                        })
                    } else {
                        recargarTabla();
                        Swal.fire({
                            position: 'center',
                            icon: 'success',
                            title: 'Voluntario aceptado',
                            text: "El voluntario  ha sido aceptado correctamente.",
                            showConfirmButton: false,
                            timer: 1500
                        })
                        $('#modalAceptar').modal('hide');
                    }
                },
                error: function (error) {
                    console.error("Error en la petición AJAX:", error);
                }
            });

        });
    }

    function cancelar(data) {
        // Abrir el modal de confirmación
        $('#confirmModal').modal('show');

        // Agregar un evento al botón de confirmación dentro del modal
        $('#confirmarBorrar').click(function () {
            // console.log('Editar usuario con ID:', id);

            console.log(data)


            $.ajax({
                type: "POST",
                url: "/voluntario",
                data: {
                    accion: "aceptarRechazar",
                    idVoluntario: data.voluntario.idVoluntario,
                    idEvaluacionOrganizacionEvento: data.evaluacionOrganizacionEvento.idEvaluacionOrganizacionEvento,
                    idEstatusAceptadoRechazado: 3
                },
                success: function (response) {
                    // Procesar la respuesta del servlet si es necesario
                    console.log("Respuesta del servidor:", response);
                    if (response.error) {
                        Swal.fire({
                            position: 'center',
                            icon: 'error',
                            title: 'Error',
                            text: "Tenemos algunos errores.",
                            showConfirmButton: false,
                            timer: 1500
                        })
                    } else {
                        recargarTabla();
                        Swal.fire({
                            position: 'center',
                            icon: 'success',
                            title: 'Voluntario rechazado',
                            text: "El voluntario  ha sido rechazado correctamente.",
                            showConfirmButton: false,
                            timer: 1500
                        })
                        $('#confirmModal').modal('hide');
                    }
                },
                error: function (error) {
                    console.error("Error en la petición AJAX:", error);
                }
            });

        });
    }

    const recargarTabla = () => {
        let table = $('#example1').DataTable();
        table.ajax.reload();
    }

</script>

<div id="modalAceptar" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmModalLabel1">Confirmar Aceptación</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                ¿Estás seguro de que deseas aceptar esta Organizacion?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button id="confirmarAceptar" type="button" class="btn btn-danger">Aceptar</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal de confirmación -->
<div id="confirmModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmModalLabel">Confirmar rechazo </h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                ¿Estás seguro de que deseas rechazar este Evento?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button id="confirmarBorrar" type="button" class="btn btn-danger">Rechazar</button>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.js"></script>