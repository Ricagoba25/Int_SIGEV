<link href="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.css" rel="stylesheet">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="content-header">
    <div class="container-fluid">
        <div class="row mb-2">
            <div class="col-sm-6">
                <h1>Eventos Publicados</h1>
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
                        <input type="hidden" id="idOrganizacion" value="${sesion.getIdOrganizacion()}">
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
                                <th>Estado del Evento</th>
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


        let id = $("#idOrganizacion").val();


        $("#formulario_evento").validate({
            errorClass: "is-invalid",
            validClass: "is-valid",
            rules: {
                nombreEvento: {
                    required: true
                },
                descripcion: {
                    required: true
                },
                fecha: {
                    required: true
                },
                calle: {
                    required: true,
                },
                noExterior: {
                    required: false
                },
                noInterior: {
                    required: false
                },
                colonia: {
                    required: true
                },
                municipio: {
                    required: true
                },
                estado: {
                    required: true
                },
            },
            messages: {
                nombreEvento: {
                    required: "El Nombre del Evento es requerido.",
                },
                descripcion: {
                    required: "La Descripcion es requerida.",
                },
                fecha: {
                    required: "La Fecha es requerida.",
                },
                calle: {
                    required: "La Calle es requerida.",
                },
                colonia: {
                    required: "La Colonia es requerida.",
                },
                municipio: {
                    required: "El Municipio es requerido.",
                },
                estado: {
                    required: "El Estado es requerido.",
                },
            },
            submitHandler: function (form) {
                // do other things for a valid form
                //editar o nuevo

                console.log("llamar funcion de enviar")

            }
        })

        const URL_API = "http://localhost:8080/"
        $('#example1').DataTable({
            "language": {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
            ajax:
                {
                    url: URL_API + 'evento?consulta=propios&idOrganizacion=' + id,
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
                    data: null,
                    render: function (data, type, row) {
                        let estatus = "Rechazado";
                        if (data.evento.estatusEvento == 1) {
                            estatus = "Pendiente";
                        }
                        if (data.evento.estatusEvento == 2) {
                            estatus = "Aceptado";
                        }
                        if (data.evento.estatusEvento == 4) {
                            estatus = "Cancelado";
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

                        let editarBtn = '<a href="#" id="editarBtn" onclick=\'editar(' + JSON.stringify(data) + ')\'> <i class="fa fa-pen"></i> </a> &nbsp;';
                        let eliminarBtn = '<a href="#" title="Eliminar Evento" onclick="eliminar(' + data.evento.idEvento + ')"> <i class="fa-solid fa-xmark"></i> </a> &nbsp;';

                        // Devolvemos los botones como una cadena HTML
                        return editarBtn + ' ' + eliminarBtn;
                    }
                }
            ]
        });


    });

    //Boton de editar Usuario
    const editar = (data) => {
        $("#formulario_evento").validate();

        // Abrir el modal de confirmación
        $('#modalEditarEvento').modal('show');

        //fill fields
        let {nombreEvento, descripcion, fecha} = data.evento;
        let calle = data.evento.direccion.calle;
        let noExterior = data.evento.direccion.noExterior;
        let noInterior = data.evento.direccion.noInterior;
        let colonia = data.evento.direccion.colonia;
        let municipio = data.evento.direccion.municipio;
        let estado = data.evento.direccion.estado.nombre;


        $('#nombreEvento').val(nombreEvento);
        $('#descripcion').val(descripcion);
        $('#fecha').val(fecha);
        $('#calle').val(calle);
        $('#noExterior').val(noExterior);
        $('#noInterior').val(noInterior);
        $('#colonia').val(colonia);
        $('#municipio').val(municipio);
        $('#estado').val(estado);


        console.log('Editar usuario con ID:', data);


    }

    //Boton de eliminar evento
    function eliminar(id) {
        // Abrir el modal de confirmación
        $('#modaleliminar').modal('show');

        // Agregar un evento al botón de confirmación dentro del modal
        $('#confirmarRechazar').click(function () {
            $.ajax({
                type: "POST",
                url: "/evento",
                data: {
                    accion: "changeStatus",
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
                            title: 'Organización aceptada',
                            text: "La organizacion ha sido aceptada correctamente.",
                            showConfirmButton: false,
                            timer: 1500
                        })
                        $('#modaleliminar').modal('hide');
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

<div id="modaleliminar" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmModalLabel2">Confirmar cancelación</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                ¿Estás seguro de que deseas dar de baja este Evento?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button id="confirmarRechazar" type="button" class="btn btn-danger">Dar de Baja</button>
            </div>
        </div>
    </div>
</div>


<!-- Modal Editar -->
<div id="modalEditarEvento" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="miModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form id="formulario_evento">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalTitle">Editar Evento</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="nombreEvento">Nombre Evento:</label>
                        <input type="text" class="form-control" id="nombreEvento" name="nombreEvento" value="">
                    </div>
                    <div class="form-group">
                        <label for="descripcion">Descripción:</label>
                        <input type="text" class="form-control" id="descripcion"
                               name="descripcion">
                    </div>
                    <div class="form-group">
                        <label for="fecha">Fecha:</label>
                        <input type="date" class="form-control" id="fecha" name="fecha">
                    </div>
                    <div class="form-group">
                        <label for="calle">Calle:</label>
                        <input type="test" class="form-control" id="calle" name="calle">
                    </div>
                    <div class="form-group">
                        <label for="noExterior"> No Exterior:</label>
                        <input type="text" class="form-control" id="noExterior" name="noExterior">
                    </div>
                    <div class="form-group">
                        <label for="noInterior"> No Interior:</label>
                        <input type="text" class="form-control" id="noInterior" name="noInterior">
                    </div>
                    <div class="form-group">
                        <label for="colonia"> Colonia:</label>
                        <input type="text" class="form-control" id="colonia" name="colonia">
                    </div>
                    <div class="form-group">
                        <label for="municipio"> Municipio:</label>
                        <input type="text" class="form-control" id="municipio" name="municipio">
                    </div>
                    <div class="form-group">
                        <label for="estado"> Estado:</label>
                        <input type="text" class="form-control" id="estado" name="estado">
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-primary"> Guardar Cambios</button>
                </div>
            </form>
        </div>
    </div>
</div>


<script src="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.min.js"></script>