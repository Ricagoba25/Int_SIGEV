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
                                <th>Apellido paterno</th>
                                <th>Apellido materno</th>
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
                //editar o nuevo
                let accion = $('#accionForm').val();
                if (accion === 'editar') {
                    enviarDatosEditar();
                } else {
                    enviarDatosNuevo();
                }

                console.log("Enviar datos" + accion)

            }
        })
        const URL_API = "http://localhost:8080/"
        $('#example1').DataTable({
            "language": {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
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


                        let editarBtn = '<a href="#" id="editarBtn" onclick=\'editar(' + JSON.stringify(data) + ')\'> <i class="fa fa-pen"></i> Editar</a> &nbsp;';
                        let borrarBtn = '<a href="#" onclick=\'borrar(' + JSON.stringify(data) + ')\'>  <i class="fa fa-trash-alt"></i> Borrar</a> &nbsp;';

                        // Devolvemos los botones como una cadena HTML
                        return editarBtn + ' ' + borrarBtn;
                    }
                }

            ]
        });
    });

    /**** registrar ***/
    const enviarDatosNuevo = () => {
        //Obtenemos los datos de los inputs para registrar en el backend
        let nombre = $('#nombre').val();
        let primerApellido = $('#primerApellido').val();
        let segundoApellido = $('#segundoApellido').val();
        let correo = $('#correo').val();
        let telefono = $('#telefono').val();

        let formData = {
            accion: 'registrar',
            nombrePersona: nombre,
            primerApellido: primerApellido,
            segundoApellido: segundoApellido,
            correo: correo,
            telefono: telefono,
            idUsuario: 0,
            idPersona: 0,
        }

        $.ajax({
            type: "POST",
            url: "/administrador",
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
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Registro exitoso',
                        text: "El administrador se ha registrado correctamente.",
                        showConfirmButton: false,
                        timer: 2500
                    })

                    let table = $('#example1').DataTable();
                    table.ajax.reload();

                    $('#modalAdministrador').modal('hide');
                }
            },
            error: function (error) {
                loading = false;
                console.error("Error en la petición AJAX:", error);
            }
        });


    }
    const nuevoAdministrador = () => {
        $("#formulario_administrador").validate().resetForm();
        //editamos el titulo y el nombre del boton submit
        $("#modalTitle").text("Registrar nuevo Administrador");
        $("#guardarCambios").text("Crear");

        $('#nombre').val("");
        $('#primerApellido').val("");
        $('#segundoApellido').val("");
        $('#correo').val("");
        $('#telefono').val("");
        //Setear Accion
        $('#accionForm').val("nuevo");
        $('#modalAdministrador').modal('show');
    }
    /**** editar ***/
    const enviarDatosEditar = () => {
        //Obtenemos los datos de los inputs para registrar en el backend
        let nombre = $('#nombre').val();
        let primerApellido = $('#primerApellido').val();
        let segundoApellido = $('#segundoApellido').val();
        let correo = $('#correo').val();
        let telefono = $('#telefono').val();

        //IDS
        let idUsuario = $('#idUsuario').val();
        let idPersona = $('#idPersona').val();

        let formData = {
            accion: 'modificar',
            nombrePersona: nombre,
            primerApellido: primerApellido,
            segundoApellido: segundoApellido,
            correo: correo,
            telefono: telefono,
            idUsuario: idUsuario,
            idPersona: idPersona
        }

        $.ajax({
            type: "POST",
            url: "/administrador",
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
                        title: 'Actualizacion exitosa',
                        text: "El administrador se ha modificado correctamente.",
                        showConfirmButton: false,
                        timer: 2500
                    })

                    recargarTabla();
                    $('#modalAdministrador').modal('hide');

                    //window.location.href = '../dashboard/index.jsp';
                }
            },
            error: function (error) {
                loading = false;
                console.error("Error en la petición AJAX:", error);
            }
        });


    }



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
        let {idPersona, nombrePersona, primerApellido, segundoApellido} = data;
        let correo = data.usuario.correo;
        let telefono = data.usuario.telefono;
        let idUsuario = data.usuario.idUsuario;

        $('#nombre').val(nombrePersona);
        $('#primerApellido').val(primerApellido);
        $('#segundoApellido').val(segundoApellido);
        $('#correo').val(correo);
        $('#telefono').val(telefono);

        //IDS
        $('#idUsuario').val(idUsuario);
        $('#idPersona').val(idPersona);


        //Setear Accion
        $('#accionForm').val("editar");


        console.log('Editar usuario con ID:', data);


    }
    //Boton de eliminar usuario
    const borrar = (data) => {
        // Abrir el modal de confirmación
        $('#confirmModal').modal('show');

        // Agregar un evento al botón de confirmación dentro del modal
        $('#confirmarBorrar').click(function () {
            console.log('Borrando usuario con ID:', data);

            let formData = {
                accion: "eliminar",
                nombrePersona: "",
                primerApellido: "",
                segundoApellido: "",
                correo: "",
                telefono: "",
                idUsuario: data.usuario.idUsuario,
                idPersona: 0
            }

            console.log(data.usuario.idUsuario)


            $.ajax({
                type: "POST",
                url: "/administrador",
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
                            title: 'Eliminación exitosa',
                            text: "El administrador se ha eliminado correctamente.",
                            showConfirmButton: false,
                            timer: 2500
                        })

                        recargarTabla();
                        $('#modalAdministrador').modal('hide');

                        //window.location.href = '../dashboard/index.jsp';
                    }
                },
                error: function (error) {
                    loading = false;
                    console.error("Error en la petición AJAX:", error);
                }
            });


            // Cerrar el modal después de borrar
            $('#confirmModal').modal('hide');
        });
    }

    const recargarTabla = () => {
        let table = $('#example1').DataTable();
        table.ajax.reload();
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
                    <h5 class="modal-title" id="modalTitle"></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <input type="hidden" value="" id="accionForm">
                    <input type="hidden" value="" id="idUsuario">
                    <input type="hidden" value="" id="idPersona">
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

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.min.js"></script>