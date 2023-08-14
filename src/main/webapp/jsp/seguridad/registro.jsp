<%--
  Created by IntelliJ IDEA.
  User: richa
  Date: 18/07/2023
  Time: 02:32 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registro</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="/assets/css/styles.css" rel="stylesheet">
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>

<div class="container">
  <div class="container__titulo">
    <h1 class="titulo text-center">SELECCIONE CON QUE CUENTA SE DESEA REGISTRAR</h1>
  </div>
  <div class="container__organizacion">
    <form>
    <div class="container__organizacion_contenido">
      <div class="row">
        <div class="col-xl-4">
          <img src="/assets/img/Logo%20SIGEV.JPG" alt="Logo" class="logo">
        </div>
        <div class="col-xl-8">
          <h3>Seleccionar tipo de Cuenta</h3>

          <div class="d-flex align-items-center align-items-center">
            <div class="form-check m-3">
              <input class="form-check-input" checked="checked" type="radio" name="formType" value="form1" onchange="changeForm(this)">
              <label class="form-check-label"> Organización</label>
            </div>
            <div class="form-check m-3">
              <input class="form-check-input" type="radio" name="formType" value="form2" onchange="changeForm(this)">
              <label class="form-check-label"> Voluntario</label>
            </div>
          </div>
        </div>
      </div>
    </div>
    </form>
  </div>

  <div id="formContainerOrganizacion">
    <div class="container">
      <div class="container__titulo">
        <h2 class="titulo text-center">REGISTRO DE ORGANIZACIÓN</h2>
      </div>
      <div class="container__formulario" style="margin-bottom: 200px">
        <div class="container__formulario_contenido">
          <form class="form-organizacion">
            <!-- Primera fila-->

            <div class="row mt-2">
              <div class="col-xl-4">
                <label for="nombre" class="form-label">Nombre:</label>
                <input type="text" name="nombre" class="form-control" id="nombre" required>
              </div>
              <div class="col-xl-4">
                <label for="razonSocial" class="form-label">Razón Social:</label>
                <input type="text" name="razonSocial" class="form-control" id="razonSocial" required>
              </div>
              <div class="col-xl-4">
                <label for="rfc" class="form-label">RFC:</label>
                <input type="text" name="rfc" class="form-control" id="rfc" required>
              </div>
            </div>
            <!-- Segunda Fila-->
            <div class="row mt-2">
              <div class="col-xl-4">
                <label for="calle" class="form-label">Calle:</label>
                <input type="text" name="calle" class="form-control" id="calle" required>
              </div>
              <div class="col-xl-3">
                <label for="noExterior" class="form-label">No Exterior:</label>
                <input type="text" name="noExterior" class="form-control" id="noExterior" required>
              </div>
              <div class="col-xl-3">
                <label for="noInterior" class="form-label">No Interior:</label>
                <input type="text" name="noInterior" class="form-control" id="noInterior" required>
              </div>
            </div>
            <!-- Tercera linea-->
            <div class="row mt-2">
              <div class="col-xl-4">
                <label for="colonia" class="form-label">Colonia:</label>
                <input type="text" name="colonia" class="form-control" id="colonia" required>
              </div>
              <div class="col-xl-4">
                <label for="municipio" class="form-label">Municipio:</label>
                <input type="text" name="municipio" class="form-control" id="municipio" required>
              </div>
              <div class="col-xl-4">
                <label for="estado" class="form-label">Estado:</label>
                <input type="text" name="estado" class="form-control" id="estado" required>
              </div>

            </div>
            <!-- Cuarta linea-->
            <div class="row mt-2">
              <div class="col-xl-4">
                <label for="telefono" class="form-label">Teléfono:</label>
                <input type="text" name="telefonoOrganizacion" class="form-control" id="telefonoOrganizacion" required>
              </div>
              <div class="col-xl-4">
                <label for="correo" class="form-label">Email:</label>
                <input type="email" name="correoOrganizacion" class="form-control" id="correoOrganizacion" required>
              </div>
              <div class="col-xl-4">
                <label for="contrasenia" class="form-label">Contraseña:</label>
                <input type="password" name="contrasenia" class="form-control" id="contrasenia">
              </div>
            </div>
            <!-- Button -->
            <div class="row justify-content-center mt-2">
              <div class="col-xl-4">
                <button type="submit" class="btn btn-primary btn-login mb-4 mt-4"> Registrarse</button>
              </div>
            </div>
          </form>
        </div>

      </div>
    </div>
  </div>

  <div id="formContainerVoluntario" style="display: none">
    <div class="container">
      <div class="container__titulo">
        <h2 class="titulo text-center">REGISTRO DE VOLUNTARIO</h2>
      </div>
      <div class="container__formulario" style="margin-bottom: 200px">
        <div class="container__formulario_contenido">
          <form id="formulario_voluntario">
            <!-- Primera fila-->
            <div class="row mt-2">

              <input type="hidden" value="" id="idUsuario">
              <input type="hidden" value="" id="idPersona">
              <input type="hidden" value="" id="idVoluntario">
              <div class="col-xl-4">
                <label for="nombreVoluntario" class="form-label">Nombre:</label>
                <input type="text" name="nombreVoluntario" class="form-control" id="nombreVoluntario" required>
              </div>
              <div class="col-xl-4">
                <label for="primerApellido" class="form-label">Apellido Paterno:</label>
                <input type="text" name="primerApellido" class="form-control" id="primerApellido" required>
              </div>
              <div class="col-xl-4">
                <label for="segundoApellido" class="form-label">Apellido Materno:</label>
                <input type="text" name="segundoApellido" class="form-control" id="segundoApellido">
              </div>
            </div>
            <!-- Segunda Fila-->
            <div class="row mt-2">
              <div class="col-xl-6">
                <label for="curp" class="form-label">Curp:</label>
                <input type="text" name="curp" class="form-control" id="curp" required>
              </div>
            </div>
            <!-- Tercera linea-->
            <div class="row mt-2">
              <div class="col-xl-4">
                <label for="telefono" class="form-label">Teléfono:</label>
                <input type="text" name="telefono" class="form-control" id="telefono" required>
              </div>
              <div class="col-xl-4">
                <label for="correo" class="form-label">Email:</label>
                <input type="text" name="correo" class="form-control" id="correo" required>
              </div>
              <div class="col-xl-4">
                <label for="contrasena" class="form-label">Contraseña:</label>
                <input type="password" name="contrasenia" class="form-control" id="contrasena" required>
              </div>
            </div>
            <!-- Button -->

            <div class="row justify-content-center mt-2">
              <div class="col-xl-4">
                <button type="submit" class="btn btn-primary btn-login mb-4 mt-4"> Registrarse</button>
              </div>
            </div>
          </form>
        </div>

      </div>
    </div>
  </div>

  <footer class="main-footer">
    <!-- To the right -->
    <div class="float-right d-none d-sm-inline">

    </div>
    <!-- Default to the left -->

  </footer>

  <script>

    $(document).ready(function () {


      $("form-organizacion").validate({
        errorClass: "is-invalid",
        validClass: "is-valid",
        rules: {
          nombreVoluntario: {
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
          nombreVoluntario: {
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
          nuevaOrganizacion();
        }
      })





      $("#formulario_voluntario").validate({
        errorClass: "is-invalid",
        validClass: "is-valid",
        rules: {
          nombreVoluntario: {
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
          nombreVoluntario: {
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
            nuevoVoluntario();
        }
      })
    });




    function changeForm(radio) {
      var formType = radio.value;
      var formContainerOrganizacion = document.getElementById("formContainerOrganizacion");
      var formContainerVoluntario = document.getElementById("formContainerVoluntario");

      console.log("formtype " + formType);
      if(formType == "form1"){
        console.log("Entro a form1");

        formContainerOrganizacion.style.display = 'block';
        formContainerVoluntario.style.display = 'none';

      } else{
        console.log("Entro a form2");
        formContainerVoluntario.style.display = 'block';
        formContainerOrganizacion.style.display = 'none';
      }
    }


    const nuevoVoluntario = () => {
      //Obtenemos los datos de los inputs para registrar en el backend
      let nombreVoluntario = $('#nombreVoluntario').val();
      let primerApellido = $('#primerApellido').val();
      let segundoApellido = $('#segundoApellido').val();
      let curp = $('#curp').val();
      let correo = $('#correo').val();
      let telefono = $('#telefono').val();
      let contrasena = $('#contrasena').val();

      let formData = {
        accion: 'registrar',
        nombrePersona: nombreVoluntario,
        primerApellido: primerApellido,
        segundoApellido: segundoApellido,
        curp: curp,
        correo: correo,
        telefono: telefono,
        contrasena: contrasena,
        idUsuario: 0,
        idPersona: 0,
        idVoluntario: 0,
      }

      console.log("formData " + formData)
      $.ajax({
        type: "POST",
        url: "/voluntario",
        data: formData,
        success: function (response) {
          loading = false;
          // Procesar la respuesta del servlet si es necesario
          console.log("Respuesta del servidor:", response);

          if (response.error) {
            Swal.fire({
              position: 'bottom-end',
              icon: 'error',
              title: 'Error',
              text: response.title,
              showConfirmButton: false,
              timer: 1500
            })
          } else {
            Swal.fire({
              position: 'center',
              icon: 'success',
              title: 'Registro exitoso',
              text: "El Voluntario se ha registrado correctamente.",
              showConfirmButton: false,
              timer: 1500
            })


          }
        },
        error: function (error) {
          loading = false;
          console.error("Error en la petición AJAX:", error);
        }
      });


    }
  </script>



</div>

</body>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.min.js"></script>
</html>
