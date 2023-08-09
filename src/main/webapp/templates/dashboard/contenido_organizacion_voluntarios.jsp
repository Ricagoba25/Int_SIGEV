<link href="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.css" rel="stylesheet">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="content-header">
  <div class="container-fluid">
    <div class="row mb-2">
      <div class="col-sm-6">
        <h1>Voluntarios</h1>
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
                <th>Nombre Voluntario</th>
                <th>Primer Apellido</th>
                <th>Segundo Apellido</th>
                <th>Correo</th>
                <th>Telefono</th>
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
        {"data": "idVoluntario"},
        {"data": "persona_idPersona.nombrePersona"},
        {"data": "persona_idPersona.primerApellido"},
        {"data": "persona_idPersona.segundoApellido"},
        {"data": "curp"},
        {"data": "usuario_idUsuario.correo"},
        {"data": "usuario_idUsuario.telefono"},
        {
          // Añadir los botones de acciones "Editar" y "Borrar"
          data: null,
          render: function (data, type, row) {
            // El contenido de esta función se ejecutará para cada celda de esta columna
            // Utilizamos data para acceder a los datos de la fila actual

            let aceptarBtn = '<a href="#" onclick="aceptar(' + data.idVoluntario + ')">  <i class="fa fa-pen"></i> </a> &nbsp;';
            let cancelarBtn = '<a href="#" onclick="cancelar(' + data.idVoluntario + ')">  <i class="fa fa-trash-alt"></i> </a>';

            // Devolvemos los botones como una cadena HTML
            return aceptarBtn + ' ' + cancelarBtn;
          }
        }

      ]

    });


  });

  function aceptar(id) {
    // Lógica para editar un usuario con el ID proporcionado
    console.log('aceptar voluntario con ID:', id);
  }

  function cancelar(id) {
    //$('#datatable').DataTable().ajax.reload();
    // Lógica para borrar un usuario con el ID proporcionado
    console.log('cancelar voluntario con ID:', id);
  }

</script>


<script src="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.js"></script>