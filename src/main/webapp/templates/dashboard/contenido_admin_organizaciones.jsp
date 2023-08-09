<link href="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.css" rel="stylesheet">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="content-header">
  <div class="container-fluid">
    <div class="row mb-2">
      <div class="col-sm-6">
        <h1>Organizaciones</h1>
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
                <th>Nombre Organización</th>
                <th>Razon Social</th>
                <th>Direccion</th>
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
        {"data": "idOrganizacion"},
        {"data": "nombreOrganizacion"},
        {"data": "razonSocial"},
        {"data": "direccion_idDireccion.estado_idEstado.nombre"},

        {
          // Añadir los botones de acciones "Editar" y "Borrar"
          data: null,
          render: function (data, type, row) {
            // El contenido de esta función se ejecutará para cada celda de esta columna
            // Utilizamos data para acceder a los datos de la fila actual

            let aceptatBtn = '<a href="#" onclick="aceptar(' + data.idPersona + ')">  <i class="fa-solid fa-clipboard-check"> </a> &nbsp;';
            let rechazarBtn = '<a href="#" onclick="rechazar(' + data.idPersona + ')">  <i class="fa-solid fa-ban"></i></i></i> </a>';

            // Devolvemos los botones como una cadena HTML
            return aceptarBtn + ' ' + rechazarBtn;
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
  function aceptar(id) {
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
  function rechazar(id) {
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
        <h5 class="modal-title" id="confirmModalLabel1">Confirmar Aceptacion</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ¿Estás seguro de que deseas aceptar esta Organizacion?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
        <button id="confirmarAceptar" type="button" class="btn btn-danger">Aceptar</button>
      </div>
    </div>
  </div>
</div>

<!-- Modal de confirmación -->
<div id="confirmModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="confirmModalLabel">Confirmar Rechazado</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ¿Estás seguro de que deseas rechazar esta organización?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
        <button id="confirmarBorrar" type="button" class="btn btn-danger">Rechazar</button>
      </div>
    </div>
  </div>
</div>



<script src="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.js"></script>