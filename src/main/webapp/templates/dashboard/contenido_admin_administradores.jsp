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

                        <div class="buttons-header text-right mb-4">
                            <button type="button" class="btn btn-success" onclick="nuevoAdministrador()"> Crear
                                adminstrador
                            </button>
                        </div>
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
    $(document).ready(function () {

        $("#formulario_administrador").validate({
            errorClass: "is-invalid",
            validClass: "is-valid",
            rules: {
                nombre: {
                    required: true
                },
                primerApellido: {
                    required: true
                },
                segundoApellido: {
                    required: true
                },
                correo: {
                    required: true,
                    email: true
                },
                telefono: {
                    required: true
                },
            },
            messages: {
                nombre: {
                    required: "El Nombre es requerido.",
                },
                primerApellido: {
                    required: "El Apellido paterno es requerido.",
                },
                segundoApellido: {
                    required: "El Apellido materno es requerido.",
                },
                correo: {
                    required: "El Correo es requerido.",
                    email: "El correo debe ser en el siguiente formato nombre@dominio.com."
                },
                telefono: {
                    required: "El Telefono es requerido.",
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


                        let editarBtn = '<a href="#" id="editarBtn" onclick=\'editar(' + JSON.stringify(data) + ')\'> <i class="fa fa-pen"></i> </a> &nbsp;';
                        let borrarBtn = '<a href="#" onclick=\'borrar(' + JSON.stringify(data) + ')\'>  <i class="fa fa-trash-alt"></i> </a> &nbsp;';

                        // Devolvemos los botones como una cadena HTML
                        return editarBtn + ' ' + borrarBtn;
                    }
                }

            ]
        });
    });

    const nuevoAdministrador = () => {
        //  let validator = $("#formulario_administrador").validate();
        //validator.resetForm();

        $("#formulario_administrador").validate().resetForm();


        //editamos el titulo y el nombre del boton submit

        $("#modalTitle").text("Registrar nuevo Administrador");
        $("#guardarCambios").text("Crear");

        $('#nombre').val("");
        $('#primerApellido').val("");
        $('#segundoApellido').val("");
        $('#correo').val("");
        $('#telefono').val("");


        $('#modalAdministrador').modal('show');
    }

    //Boton de editar Usuario
    const editar = (data) => {
        $("#formulario_administrador").validate().resetForm();
        //let validator = $("#formulario_administrador").validate();
        //validator.resetForm();


        //editamos el titulo y el nombre del boton submit
        $("#modalTitle").text("Editar Administrador");
        $("#guardarCambios").text("Actualizar");

        // Abrir el modal de confirmación
        $('#modalAdministrador').modal('show');

        //fill fields
        let {nombrePersona, primerApellido, segundoApellido} = data;
        let correo = data.usuario.correo;
        let telefono = data.usuario.telefono;

        $('#nombre').val(nombrePersona);
        $('#primerApellido').val(primerApellido);
        $('#segundoApellido').val(segundoApellido);
        $('#correo').val(correo);
        $('#telefono').val(telefono);


        console.log('Editar usuario con ID:', data);


    }
    //Boton de eliminar usuario
    const borrar = (id) => {
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
<div id="confirmModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel"
     aria-hidden="true">
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

<div id="modalAdministrador" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="miModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form id="formulario_administrador">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalTitle">Editar Administrador</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label for="nombre">Nombre:</label>
                        <input type="text" class="form-control" id="nombre" name="nombre">
                    </div>
                    <div class="form-group">
                        <label for="primerApellido">Apellido Paterno:</label>
                        <input type="text" class="form-control" id="primerApellido"
                               name="primerApellido">
                    </div>
                    <div class="form-group">
                        <label for="segundoApellido">Apellido Materno:</label>
                        <input type="text" class="form-control" id="segundoApellido" name="segundoApellido">
                    </div>
                    <div class="form-group">
                        <label for="correo">Correo:</label>
                        <input type="email" class="form-control" id="correo" name="correo">
                    </div>
                    <div class="form-group">
                        <label for="telefono">Teléfono:</label>
                        <input type="text" class="form-control" id="telefono" name="telefono">
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-primary"><span id="guardarCambios"> </span></button>
                </div>
            </form>
        </div>
    </div>
</div>


<script src="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.min.js"></script>