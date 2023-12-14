<link href="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.css" rel="stylesheet">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="content-header">
    <div class="container-fluid">
        <div class="row mb-2">
            <div class="col-sm-6">
                <h1>Evaluaciones</h1>
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
                                <th>Fecha de registro</th>
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
<div id="modalVerPreguntas" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="miModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form id="formulario_preguntas">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalTitle">Preguntas</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="idEvaluationQuestion" value="">
                    <div class="mb-3">
                        <label for="nameEvaluation" class="form-label">Nombre de la evaluación:</label>
                        <input type="text" class="form-control" id="nameEvaluationQuestion"
                               name="nameEvaluationQuestion" required>
                    </div>
                    <div class="containerShowquestions">
                        <%--                        <div class="containerShowquestion">--%>
                        <%--                            <label class="form-label">Pregunta 1:</label>--%>
                        <%--                            <input type="text" class="form-control " name="" required>--%>
                        <%--                        </div>--%>
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
                    ' <div class="question">' +
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

        $('#tableEvaluaciones').DataTable({
            "language": {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
            ajax:
                {
                    url: URL_API + 'evaluacion?consulta=evaluacionesPorOrganizacion&idOrganizacion=' + id,
                    dataSrc: ''
                },
            columns: [
                {"data": "idEvaluacion"},
                {"data": "nombreEvaluacion"},
                {
                    // Añadir los botones de acciones "Editar" y "Borrar"
                    data: null,
                    render: function (data, type, row) {
                        let d = new Date(data.fechaRegistro);
                        let ye = new Intl.DateTimeFormat('es', { year: 'numeric' }).format(d);
                        let mo = new Intl.DateTimeFormat('es', { month: 'short' }).format(d);
                        let da = new Intl.DateTimeFormat('es', { day: 'numeric' }).format(d);
                        let ho =  new Intl.DateTimeFormat('es', { timeStyle: 'medium' }).format(d);

                        return da+"-"+mo+"-"+ye+" " +ho
                    }
                },
                {
                    data: null,
                    render: function (data, type, row) {
                        let estatus = "Activa";
                        if (data.estatusEvaluacion == 2) {
                            estatus = "Inactiva";
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
                        let verRespustas = '<a href="#" id="editarBtn" onclick=\'verDatos(' + JSON.stringify(data) + ')\'> <i class="fa fa-eye"></i> Ver preguntas <br></a>';


                        let aceptarBtn = '<a href="#" title="Activar" onclick="eliminar(' + data + ')"> <i class="fa fa-check"></i> Activar </a>';
                        let rechazarBtn = '<a href="#" title="Desactivar" onclick="eliminar(' + data + ')"> <i class="fa fa-times"></i> Desactivar</a> &nbsp;';

                        let btns = verRespustas + ' ' + rechazarBtn
                        if (data.estatusOrganizacion == 2) {
                            btns += verRespustas + ' ' + aceptarBtn
                        }
                        return btns;
                    }
                }
            ]
        });


    });

    function verDatos(datos) {
        // clear container
        let preguntasContainer = $('.containerShowquestions');
        preguntasContainer.empty();

        $('#nameEvaluationQuestion').val(datos.nombreEvaluacion)
        $('#idEvaluationQuestion').val(datos.idEvaluacion)
        $.each(datos.preguntas, function (index, pregunta) {
            let consecutivo = index +1
            let newQuestionHtml =
                '<div class="containerShowquestion">' +
                '   <label for="question' + consecutivo + '" class="form-label mt-2">Pregunta ' + consecutivo + ':</label>' +
                '   <input type="text" class="form-control  questionShow"  id="' + pregunta.idPregunta + '" name="question" required value="' + pregunta.textoPregunta + '">' +
                '</div>';
            $('.containerShowquestions').append(newQuestionHtml);
        });

        // Abrir el modal de confirmación
        $('#modalVerPreguntas').modal('show');

    }

    //Boton de editar Usuario
    function editar(data) {
        $("#formulario_evento").validate();

        // Abrir el modal de confirmación
        $('#modalVerPreguntas').modal('show');

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

    $("#modalVerPreguntas").submit(function (event) {
        event.preventDefault()

        let preguntas = [];
        let idPreguntas = [];
        $(".questionShow").each(function (index) {
            preguntas.push($(this).val());

            idPreguntas.push($(this).attr('id'));
        });

        let output = {
            accion: "modificar",
            idEvaluacion: $('#idEvaluationQuestion').val(),
            nombreEvaluacion: $('#nameEvaluationQuestion').val(),
            preguntas,
            idPreguntas
        }

        console.log(output)

        $.ajax({
            type: "POST",
            url: "/evaluacion",
            data: output,
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
                        title: 'Evaluación modificada',
                        text: "La evaluación ha sido modificada correctamente.",
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


        // Cerrar el modal después de obtener las respuestas
        $('#modalVerPreguntas').modal('hide');
        $(".questionInput").val('');
        $("#idEvaluationQuestion").val('');
        $("#nameEvaluation").val('');
    })

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
        let formData = [];
        $(".questionInput").each(function (index) {
            formData.push($(this).val());
        });


        let output = {
            accion: "registrar",
            idOrganizacion: $("#idOrganizacion").val(),
            nombreEvaluacion: $("#nameEvaluation").val(),
            preguntas: formData
        }
        // formData.name = $("#nameEvaluation").val();

        $.ajax({
            type: "POST",
            url: "/evaluacion",
            data: output,
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
                        title: 'Evaluación creada',
                        text: "La evaluación ha sido creada correctamente.",
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