<link href="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.css" rel="stylesheet">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="content-header mt-5">
    <div class="container-fluid">
        <div class="row mb-2">
            <div class="col-sm-6">
                <h1>Eventos Disponibles</h1>
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
                        <table id="example2" class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre Evento</th>
                                <th>Descripción</th>
                                <th>Fecha</th>
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


<script>
    $(function () {
        const URL_API = "http://localhost:8080/"
        $('#example2').DataTable({
            ajax:
                {
                    url: URL_API + 'evento?consulta=eventosActivos',
                    dataSrc: ''
                },
            columns: [
                {"data": "evento.idEvento"},
                {"data": "evento.nombreEvento"},
                {"data": "evento.descripcion"},
                {"data": "evento.fecha"},
                {"data": "evento.direccion.municipio"},
                {"data": "evento.direccion.estado.nombre"},

                {
                    // Añadir los botones de acciones "Editar" y "Borrar"
                    data: null,
                    render: function (data, type, row) {
                        // El contenido de esta función se ejecutará para cada celda de esta columna
                        // Utilizamos data para acceder a los datos de la fila actual

                        let postularseBtn = '<a href="#" title="Postularse" onclick="postularse(' + data.evento.idEvento + ')"> <i class="fa-solid fa-thumbs-up"></i> </a> &nbsp;';

                        // Devolvemos los botones como una cadena HTML
                        return postularseBtn;
                    }
                }
            ]
        });


    });

    //Boton de postularse al Evento
    function postularse(id) {
        // Abrir el modal de confirmación
        $('#modalPostularse').modal('show');

        // Agregar un evento al botón de confirmación dentro del modal
        $('#guardarCambios').click(function () {
            console.log('Editar usuario con ID:', id);

            // Cerrar el modal después de borrar
            $('#modalPostularse').modal('hide');

            // Recargar la tabla o realizar otras acciones necesarias
            //$('#datatable').DataTable().ajax.reload();
        });
    }


</script>

<div id="modalPostularse" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="miModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="miModalLabel">Proceso de Postulación</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p> Aqui iran las preguntas de la evaluación</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button id="guardarCambios" type="button" class="btn btn-primary">Postularme</button>
            </div>
        </div>
    </div>
</div>


<script src="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.js"></script>