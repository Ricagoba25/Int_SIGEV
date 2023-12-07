<link href="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.css" rel="stylesheet">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="content-header">
    <div class="container-fluid">
        <div class="row mb-2">
            <div class="col-sm-6">
                <h1>Crear Evaluación</h1>
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
                            <button type="button" class="btn btn-success" onclick="crearEvaluacion()"> Crear
                                evaluación
                            </button>
                        </div>
                        <input type="hidden" id="idOrganizacion" value="${sesion.getIdOrganizacion()}">
                        <table id="tableEvaluaciones" class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre de la evaluación</th>
                                <th>Fecha de creación</th>
<%--                                <th>Fecha</th>--%>
<%--                                <th>Calle</th>--%>
<%--                                <th>No Exterior</th>--%>
<%--                                <th>No Interior</th>--%>
<%--                                <th>Colonia</th>--%>
<%--                                <th>Municipio</th>--%>
<%--                                <th>Estado</th>--%>
<%--                                <th>Estado del Evento</th>--%>
<%--                                <th>Acciones</th>--%>

                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


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

<%--modal crear evaluación--%>
<div id="modalCrearEvaluacion" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="miModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <form id="formulario_evaluacion">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalTitleEvl">Crear Evaluación</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                        <div class="mb-3">
                            <label for="nameEvaluation" class="form-label">Nombre de la evaluación:</label>
                            <input type="text" class="form-control" id="nameEvaluation" name="nameEvaluation" required>
                        </div>
                        <div id="questionsContainer" class="questionsContainer">
                            <div class="questionContainer">
                                <label class="form-label">Pregunta 1:</label>
                                <input type="text" class="form-control questionInput" name="question[]" required>
                            </div>
                        </div>
                        <button type="button" class="btn btn-primary mt-3" id="btnAddQuestion">Agregar Pregunta</button>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-primary"> Guardar Cambios</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        let id = $("#idOrganizacion").val();

        $("#btnAddQuestion").click(function () {
            let maxQuestions = 10;
            let questionCount = $(".questionInput").length + 1;

            if (questionCount <= maxQuestions) {
                let newQuestionHtml =
                    '<div class="questionContainer">' +
                        '<label for="question' + questionCount + '" class="form-label mt-2">Pregunta ' + questionCount + ':</label>' +
                        ' <div class="question">'+
                        '<input type="text" class="form-control  questionInput" name="question[]" required>' +
                        ' <button type="button" class="btn btn-danger btnRemoveQuestion"><i class="fa fa-remove"></i></button>' +
                    '</div>';

                $("#questionsContainer").append(newQuestionHtml);
            } else {
                Swal.fire({
                    position: 'center',
                    icon: 'error',
                    title: 'Error',
                    text: "No puede agregar más de 10 preguntas",
                    showConfirmButton: false,
                    timer: 1500
                })
            }
        })
        $("#questionsContainer").on("click", ".btnRemoveQuestion", function () {
            $(this).closest(".questionContainer").remove();
            updateQuestionNumbers();
        });

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
        // $('#tableEvaluaciones').DataTable({
        //     "language": {
        //         "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
        //     },
        //     ajax:
        //         {
        //             url: URL_API + 'evento?consulta=propios&idOrganizacion=' + id,
        //             dataSrc: ''
        //         },
        //     columns: [
        //         {"data": "evento.idEvaluacion"},
        //         {"data": "evento.nombreEvaluacion"},
        //         {"data": "evento.descripcion"},
        //
        //         {
        //             data: null,
        //             render: function (data, type, row) {
        //                 let estatus = "Rechazado";
        //                 if (data.evento.estatusEvento == 1) {
        //                     estatus = "Pendiente";
        //                 }
        //                 if (data.evento.estatusEvento == 2) {
        //                     estatus = "Aceptado";
        //                 }
        //                 if (data.evento.estatusEvento == 4) {
        //                     estatus = "Cancelado";
        //                 }
        //                 return estatus;
        //             }
        //         },
        //         {
        //             // Añadir los botones de acciones "Editar" y "Borrar"
        //             data: null,
        //             render: function (data, type, row) {
        //                 // El contenido de esta función se ejecutará para cada celda de esta columna
        //                 // Utilizamos data para acceder a los datos de la fila actual
        //
        //                 let editarBtn = '<a href="#" id="editarBtn" onclick=\'editar(' + JSON.stringify(data) + ')\'> <i class="fa fa-pen"></i> Editar <br></a>';
        //                 let eliminarBtn = '<a href="#" title="Eliminar Evento" onclick="eliminar(' + data.evento.idEvento + ')"> <i class="fa-solid fa-xmark"></i> Eliminar</a> &nbsp;';
        //
        //                 // Devolvemos los botones como una cadena HTML
        //                 return editarBtn + ' ' + eliminarBtn;
        //             }
        //         }
        //     ]
        // });


    });

    //Boton de editar Usuario
    function editar(data) {
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

    function crearEvaluacion() {
        $('#modalCrearEvaluacion').modal('show');
    }
    function recargarTabla() {
        let table = $('#tableEvaluaciones').DataTable();
        table.ajax.reload();
    }

    function updateQuestionNumbers() {
        $(".questionContainer").each(function (index) {
            let questionNumber = index + 1;
            $(this).find('label').text('Pregunta ' + questionNumber + ':');
        });
    }
    //save data (create evaluation)
    $("#modalCrearEvaluacion").submit(function (event) {
        event.preventDefault();

        // get answer and questions (form)
        let formData = {};
        $(".questionInput").each(function (index) {
            formData['pregunta' + (index + 1)] = $(this).val();
        });
        formData.name = $("#nameEvaluation").val();


        console.log(formData);

        // Cerrar el modal después de obtener las respuestas
        $('#modalCrearEvaluacion').modal('hide');
        $(".questionInput").val('');
        $("#nameEvaluation").val('');
    });
    // formulario_evaluacion


</script>

<style>
    .question {
        display: grid;
        grid-template-columns: 90% auto;
        gap: 20px;

    }
</style>
<script src="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.min.js"></script>