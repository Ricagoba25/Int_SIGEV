<link href="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.css" rel="stylesheet">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="content-header">
    <div class="container-fluid">
        <div class="row mb-2">
            <div class="col-sm-6">
                <h1>Voluntarios</h1>
            </div>
        </div>
    </div>
</section>

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
                                <th>Nombre voluntario</th>
                                <th>Apellido paterno</th>
                                <th>Apellido materno</th>
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
    $(function () {
        const URL_API = "http://localhost:8080/"
        $('#example1').DataTable({
            "language": {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
            ajax:
                {
                    url: URL_API + 'voluntario?consulta=todosVoluntarios',
                    dataSrc: ''
                },
            columns: [
                {"data": "idVoluntario"},
                {"data": "persona.nombrePersona"},
                {"data": "persona.primerApellido"},
                {"data": "persona.segundoApellido"},
                {"data": "curp"},
                {"data": "persona.usuario.correo"},
                {"data": "persona.usuario.telefono"},
                {
                    data: null,
                    render: function (data, type, row) {
                        let estatus = "Bloqueado";
                        if (data.estatusVoluntario == 1) {
                            estatus = "Pendiente";
                        }
                        if (data.estatusVoluntario == 2) {
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

                        let bloquearBtn = '<a href="#" title="Bloquear" onclick="bloquear(' + data.idVoluntario + ')">  <i class="fa-solid fa-ban"></i> Bloquear </a> &nbsp;';


                        // Devolvemos los botones como una cadena HTML
                        return bloquearBtn;
                    }
                }

            ]

        });


    });

    function bloquear(idVoluntario) {
        // Abrir el modal de confirmación
        $('#modalcancelar').modal('show');
        console.log("idVoluntario");
        console.log(idVoluntario);


        // Cerrar el modal después de cancelar
        $('#modalcancelar').modal('hide');

        // Recargar la tabla o realizar otras acciones necesarias
        //$('#datatable').DataTable().ajax.reload();
        $('#confirmarRechazar').click(function () {

            $.ajax({
                type: "POST",
                url: "/voluntario",
                data: {
                    accion: "bloquear",
                    idVoluntario: idVoluntario,
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
                            title: 'Bloqueo exitoso',
                            text: "Has bloqueado exitosamente al voluntario.",
                            showConfirmButton: false,
                            timer: 1500
                        })
                        $('#modalcancelar').modal('hide');
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

<div id="modalcancelar" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmModalLabel2">Confirmar Bloqueo</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                ¿Estás seguro de que deseas bloquear al voluntario?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button id="confirmarRechazar" type="button" class="btn btn-danger">Bloquear</button>
            </div>
        </div>
    </div>
</div>


<script src="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>