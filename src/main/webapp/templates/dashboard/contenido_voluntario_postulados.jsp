<link href="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.css" rel="stylesheet">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="content-header">
    <div class="container-fluid">
        <div class="row mb-2">
            <div class="col-sm-6">
                <h1>Eventos Postulados</h1>
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
                        <input type="hidden" id="idVoluntario" value="${sesion.getIdVoluntario()}">
                        <table id="example1" class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre Evento</th>
                                <th>Descripción</th>
                                <th>Fecha</th>
                                <th>Calle</th>
                                <th>No Exterior</th>
                                <th>No Interior</th>
                                <th>Colonia</th>
                                <th>Municipio</th>
                                <th>Estado</th>
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

<div id="modalcancelar" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmModalLabel2">Confirmar Cancelación</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                ¿Estás seguro de que deseas darte de baja en esta Postulación?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button id="confirmarRechazar" type="button" class="btn btn-danger">Darme de Baja</button>
            </div>
        </div>
    </div>
</div>


<script>
    $(function () {
        const URL_API = "http://localhost:8080/"

        let id = $("#idVoluntario").val()
        $('#example1').DataTable({
            "language": {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
            ajax:
                {
                    url: URL_API + 'evento?consulta=voluntarioAceptado&idVoluntario=' + id,
                    dataSrc: ''
                },
            columns: [
                {"data": "evento.idEvento"},
                {"data": "evento.nombreEvento"},
                {"data": "evento.descripcion"},
                {"data": "evento.fecha"},
                {"data": "evento.direccion.calle"},
                {"data": "evento.direccion.noExterior"},
                {"data": "evento.direccion.noInterior"},
                {"data": "evento.direccion.colonia"},
                {"data": "evento.direccion.municipio"},
                {"data": "evento.direccion.estado.nombre"},
                {
                    // Añadir los botones de acciones "Editar" y "Borrar"
                    data: null,
                    render: function (data, type, row) {
                        let cancelarBtn = '<a href="#" title="Cancelar Postulación" onclick="cancelar(' + data.evento.idEvento + ')"> <i class="fa-solid fa-xmark"></i> Cancelar</a> &nbsp;';

                        // Devolvemos los botones como una cadena HTML
                        return cancelarBtn;
                    }
                }
            ]
        });


    });

    //Boton de postularse al Evento
    function cancelar(id) {
        // Abrir el modal de confirmación
        $('#modalcancelar').modal('show');

        // Agregar un evento al botón de confirmación dentro del modal


        $.ajax({
            type: "POST",
            url: "/voluntario",
            data: {
                accion: "postular",
                idEvento: id,
                estatusEvento: 4
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
                        title: 'Postulación exitosa',
                        text: "Te haz postulado correctamente.",
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

        // Recargar la tabla o realizar otras acciones necesarias
        //$('#datatable').DataTable().ajax.reload();

    }

    const recargarTabla = () => {
        let table = $('#example1').DataTable();
        table.ajax.reload();
    }


</script>


<script src="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.js"></script>