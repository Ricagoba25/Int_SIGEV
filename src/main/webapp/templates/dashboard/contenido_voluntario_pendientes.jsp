<link href="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.css" rel="stylesheet">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="content-header">
  <div class="container-fluid">
    <div class="row mb-2">
      <div class="col-sm-6">
        <h1>Eventos Pendientes</h1>
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


<script>
  $(function () {
    const URL_API = "http://localhost:8080/"

    let id = $("#idVoluntario").val()
    $('#example1').DataTable({
      ajax:
              {
                url: URL_API + 'evento?consulta=voluntarioPendiente&idVoluntario='+id,
                dataSrc: ''
              },
      columns: [
        {"data": "evaluacionOrganizacionEvento.evento.idEvento"},
        {"data": "evaluacionOrganizacionEvento.evento.nombreEvento"},
        {"data": "evaluacionOrganizacionEvento.evento.descripcion"},
        {"data": "evaluacionOrganizacionEvento.evento.fecha"},
        {"data": "evaluacionOrganizacionEvento.evento.direccion.calle"},
        {"data": "evaluacionOrganizacionEvento.evento.direccion.noExterior"},
        {"data": "evaluacionOrganizacionEvento.evento.direccion.noInterior"},
        {"data": "evaluacionOrganizacionEvento.evento.direccion.colonia"},
        {"data": "evaluacionOrganizacionEvento.evento.direccion.municipio"},
        {"data": "evaluacionOrganizacionEvento.evento.direccion.estado.nombre"},
        {
          // Añadir los botones de acciones "Editar" y "Borrar"
          data: null,
          render: function (data, type, row) {


            console.log(data)

            let cancelarBtn = '<a href="#" title="Cancelar Postulación" onclick="cancelar(' + data.evaluacionOrganizacionEvento.evento.idEvento + ')"> <i class="fa-solid fa-xmark"></i> Cancelar</a> &nbsp;';

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
    $('#confirmareditar').click(function () {
      console.log('Editar usuario con ID:', id);

      // Cerrar el modal después de cancelar
      $('#confirmModal').modal('hide');

      // Recargar la tabla o realizar otras acciones necesarias
      //$('#datatable').DataTable().ajax.reload();
    });
  }





</script>

<div id="modalcancelar" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
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


<script src="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.js"></script>