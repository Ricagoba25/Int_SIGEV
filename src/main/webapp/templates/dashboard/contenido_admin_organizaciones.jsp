<link href="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.css" rel="stylesheet">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="content-header">
    <div class="container-fluid">
        <div class="row mb-2">
            <div class="col-sm-6">
                <h1>Organizaciones</h1>
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
                        <div class="table-responsive">
                            <table id="dataTableOrganizacion" class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nombre organización</th>
                                    <th>Razon social</th>
                                    <th>RFC</th>
                                    <th>Correo</th>
                                    <th>Colonia</th>
                                    <th>Estado</th>
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
    </div>
</section>


<script>
    $(function () {
        const URL_API = "http://localhost:8080/"
        $('#dataTableOrganizacion').DataTable({
            "language": {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
            ajax:
                {
                    url: URL_API + 'organizacion',
                    dataSrc: ''
                },
            columns: [
                {"data": "idOrganizacion"},
                {"data": "nombreOrganizacion"},
                {"data": "razonSocial"},
                {"data": "rfc"},
                {"data": "usuario.correo"},
                {"data": "direccion.colonia"},
                {"data": "direccion.estado.nombre"},
                {
                    data: null,
                    render: function (data, type, row) {
                        let estatus = "Rechazado";
                        if (data.estatusOrganizacion == 1) {
                            estatus = "Pendiente";
                        }
                        if (data.estatusOrganizacion == 2) {
                            estatus = "Aceptado";
                        }
                        return estatus;
                    }
                    // {"data": "estatusOrganizacion"},
                },
                {
                    // Añadir los botones de acciones "Editar" y "Borrar"
                    data: null,
                    render: function (data, type, row) {
                        // El contenido de esta función se ejecutará para cada celda de esta columna
                        // Utilizamos data para acceder a los datos de la fila actual

                        let aceptatBtn = '<a href="#" title="Aceptar" onclick="aceptar(' + data.idOrganizacion + ')"> <i class="fa fa-check"></i> Aceptar <br></a>';
                        let rechazarBtn = '<a href="#" title="Rechazar" onclick="rechazar(' + data.idOrganizacion + ')"> <i class="fa fa-times"></i> Rechazar</a> &nbsp;';

                        let btns = aceptatBtn
                        if (data.estatusOrganizacion == 1) {
                            btns = aceptatBtn + ' ' + rechazarBtn
                        } else if (data.estatusOrganizacion == 2) {
                            btns = rechazarBtn
                        }

                        // Devolvemos los botones como una cadena HTML
                        return btns;
                    }
                }

            ]

        });


    });

    /*function editar(id) {
        // Lógica para editar un usuario con el ID proporcionado
        console.log('Editar usuario con ID:', id);
    }*/

    //Boton de editar Usuario
    function aceptar(id) {
        // Abrir el modal de confirmación
        $('#modalAceptar').modal('show');

        // Agregar un evento al botón de confirmación dentro del modal
        $('#confirmarAceptar').click(function () {
            $.ajax({
                type: "POST",
                url: "/organizacion",
                data: {
                    accion: "changeStatus",
                    idOrganizacion: id,
                    estatus: 2
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
                            title: 'Organización aceptada',
                            text: "La organizacion ha sido aceptada correctamente.",
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

    //Boton de eliminar usuario
    function rechazar(id) {
        // Abrir el modal de confirmación
        $('#modalRechazar').modal('show');

        // Agregar un evento al botón de confirmación dentro del modal
        $('#confirmarRechazar').click(function () {
            $.ajax({
                type: "POST",
                url: "/organizacion",
                data: {
                    accion: "changeStatus",
                    idOrganizacion: id,
                    estatus: 3
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
                            title: 'Organización rechazada',
                            text: "La organizacion se ha rechazada correctamente.",
                            showConfirmButton: false,
                            timer: 1500
                        })
                        $('#modalRechazar').modal('hide');
                    }
                },
                error: function (error) {
                    console.error("Error en la petición AJAX:", error);
                }
            });
        });
    }

    const recargarTabla = () => {
        let table = $('#dataTableOrganizacion').DataTable();
        table.ajax.reload();
    }

</script>
<!-- Modal de confirmación -->
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
<div id="modalRechazar" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmModalLabel2">Confirmar Rechazado</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                ¿Estás seguro de que deseas rechazar esta Organización?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button id="confirmarRechazar" type="button" class="btn btn-danger">Aceptar</button>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.js"></script>