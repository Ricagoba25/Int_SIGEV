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
                        <input type="hidden" id="idVoluntario" value="${sesion.getIdVoluntario()}">
                        <div class="table-responsive">


                            <table id="example2" class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nombre evento</th>
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
    </div>
</section>

<div id="modalPostularse" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="miModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="miModalLabel">Proceso de postulación</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="container-answers">

                </div>
                <%--                <p> ¿Estás seguro que deseas postularte?</p>--%>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <button id="guardarCambios" type="button" class="btn btn-primary">Postularme</button>
            </div>
        </div>
    </div>
</div>


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

<script>
    $(document).ready(function () {

        const URL_API = "http://localhost:8080/"
        let id = $("#idVoluntario").val();

        $('#example2').DataTable({
            "language": {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
            },
            ajax:
                {
                    // url: URL_API + 'evento?consulta=todos',
                    url: URL_API + 'evento?consulta=eventosActivos&idVoluntario=' + id,
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

                        let postularseBtn = '<a href="#" title="Aceptar" onclick=\'postularse(' + JSON.stringify(data.evaluacion) + ')\'>  <i class="fa-solid fa-thumbs-up"></i> Postularse</a>';


                        // let postularseBtn = '<a href="#" title="Postularse" onclick="postularse(' + data + ')"> <i class="fa-solid fa-thumbs-up"></i> Postularse</a> &nbsp;';

                        // Devolvemos los botones como una cadena HTML
                        return postularseBtn;
                    }
                }
            ]
        });


    });

    //Boton de postularse al Evento
    function postularse(datos) {
        // Abrir el modal de confirmación
        $('#modalVerPreguntas').modal('show');
        let idVoluntario = $("#idVoluntario").val();


        // clear container
        let preguntasContainer = $('.containerShowquestions');
        preguntasContainer.empty();

        $('#nameEvaluationQuestion').val(datos.nombreEvaluacion)
        $('#idEvaluationQuestion').val(datos.idEvaluacion)
        $.each(datos.preguntas, function (index, pregunta) {
            let consecutivo = index +1
            let newQuestionHtml =
                '<div class="containerShowquestion">' +
                '   <label for="question' + consecutivo + '" class="form-label mt-2">' + pregunta.textoPregunta  + ':</label>' +
                '   <input type="text" class="form-control  questionShow"  id="' + pregunta.idPregunta + '" name="question" required >' +
                '</div>';
            $('.containerShowquestions').append(newQuestionHtml);
        });

        // Abrir el modal de confirmación
        $('#modalVerPreguntas').modal('show');


    }

    const recargarTabla = () => {
        let table = $('#example2').DataTable();
        table.ajax.reload();
    }


</script>


<script src="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.min.js"></script>