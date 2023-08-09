<link href="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.css" rel="stylesheet">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="content-header">
    <div class="container-fluid">
        <div class="row mb-2">
            <div class="col-sm-6">
                <h1>Administradores</h1>
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
                                <th>Nombre</th>
                                <th>Apellido Paterno</th>
                                <th>Apellido Materno</th>
                                <th>Correo</th>
                                <th>Teléfono</th>
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
            ajax:
                {
                    url: URL_API + 'administrador',
                    dataSrc: ''
                },
            columns: [
                {"data": "idPersona"},
                {"data": "nombrePersona"},
                {"data": "primerApellido"},
                {"data": "segundoApellido"},
                {"data": "usuario.correo"},
                {"data": "usuario.telefono"},
                {
                    // Añadir los botones de acciones "Editar" y "Borrar"
                    data: null,
                    render: function (data, type, row) {
                        // El contenido de esta función se ejecutará para cada celda de esta columna
                        // Utilizamos data para acceder a los datos de la fila actual

                        let editarBtn = '<a href="#" onclick="editar(' + data.idPersona + ')">  <i class="fa fa-pen"></i> </a> &nbsp;';
                        let borrarBtn = '<a href="#" onclick="borrar(' + data.idPersona + ')">  <i class="fa fa-trash-alt"></i> </a>';

                        // Devolvemos los botones como una cadena HTML
                        return editarBtn + ' ' + borrarBtn;
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
    function editar(id) {
        // Abrir el modal de confirmación
        $('#modalEditar').modal('show');

        // Agregar un evento al botón de confirmación dentro del modal
        $('#confirmareditar').click(function () {
            console.log('Editar usuario con ID:', id);

            // Cerrar el modal después de borrar
            $('#confirmModal').modal('hide');

            // Recargar la tabla o realizar otras acciones necesarias
            //$('#datatable').DataTable().ajax.reload();
        });
    }
    //Boton de eliminar usuario
    function borrar(id) {
        // Abrir el modal de confirmación
        $('#confirmModal').modal('show');

        // Agregar un evento al botón de confirmación dentro del modal
        $('#confirmarBorrar').click(function () {
            console.log('Borrando usuario con ID:', id);

            // Cerrar el modal después de borrar
            $('#confirmModal').modal('hide');

            // Recargar la tabla o realizar otras acciones necesarias
            //$('#datatable').DataTable().ajax.reload();
        });
    }

</script>
<!-- Modal de confirmación -->
<div id="confirmModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmModalLabel">Confirmar Eliminado</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                ¿Estás seguro de que deseas borrar este Administrador?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button id="confirmarBorrar" type="button" class="btn btn-danger">Borrar</button>
            </div>
        </div>
    </div>
</div>

<div id="modalEditar" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="miModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="miModalLabel">Editar Administrador</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="formulario">
                    <div class="form-group">
                        <label for="nombre">Nombre:</label>
                        <input type="text" value="${nombre}" class="form-control" id="nombre" >
                    </div>
                    <div class="form-group">
                        <label for="primerApellido">Apellido Paterno:</label>
                        <input type="text" value="${primerApellido}" class="form-control" id="primerApellido" >
                    </div>
                    <div class="form-group">
                        <label for="segundoApellido">Apellido Materno:</label>
                        <input type="text" value="${segundoApellido}" class="form-control" id="segundoApellido" >
                    </div>
                    <div class="form-group">
                        <label for="correo">Correo:</label>
                        <input type="email" value="${correo}" class="form-control" id="correo" >
                    </div>
                    <div class="form-group">
                        <label for="telefono">Teléfono:</label>
                        <input type="tel" value="${telefono}" class="form-control" id="telefono">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button id="guardarCambios" type="button" class="btn btn-primary">Actualizar</button>
            </div>
        </div>
    </div>
</div>



<script src="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.js"></script>