<link href="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.css" rel="stylesheet">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<section class="content">
    <!-- Main content -->
    <c:if test="${not empty sesion}">
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4" style="margin-left: 100px">
            <div class=" pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2 mt-4 text-center" id="nombreCompleto">${sesion.getNombreOrganizacion()}</h1>
            </div>
            <!--Contenido-->

            <!-- Logo Imagen-->
            <div class="m-auto mt-5 mb-4 text-center">
                <div>
                    <img src="${sesion.getImagenLogotipo()}" alt="FotoPerfil" class="fotoPerfil rounded-circle">
                </div>
                <div>
                    <a href="#" class="text-center" data-bs-toggle="modal" data-bs-target="#exampleModal">Cambiar
                        Logo</a>
                </div>
            </div>

            <!--Formulario-->
            <div class="container__formulario">
                <div class="container__formulario_contenido">
                    <!-- Primera fila-->
                    <form id="form_organizacion">
                        <input type="hidden" id="idOrganizacion" value="${sesion.getIdOrganizacion()}">
                        <input type="hidden" id="idUsuario" value="${sesion.getUsuario().getIdUsuario()}">
                        <div class="row mt-2">
                            <div class="col-xl-4">
                                <label for="nombre" class="form-label">Nombre:</label>
                                <input type="text" value="${sesion.getNombreOrganizacion()}" name="nombre"
                                       class="form-control" id="nombre">
                            </div>
                            <div class="col-xl-4">
                                <label for="razonSocial" class="form-label">Razón Social*:</label>
                                <input type="text" class="form-control" value="${sesion.getRazonSocial()}"
                                       name="razonSocial"
                                       id="razonSocial">
                            </div>
                            <div class="col-xl-4">
                                <label for="rfc" class="form-label">RFC*:</label>
                                <input type="text" value="${sesion.getRfc()}" name="rfc" class="form-control" id="rfc" readonly>
                            </div>
                        </div>
                        <!-- Segunda Fila-->
                        <div class="row mt-2">
                            <div class="col-xl-4">
                                <label for="telefono" class="form-label">Teléfono:</label>
                                <input type="text" value="${usuario.getTelefono()}" name="telefono" class="form-control"
                                       id="telefono">
                            </div>
                            <div class="col-xl-8">
                                <label for="correo" class="form-label">Email*:</label>
                                <input type="text" value="${usuario.getCorreo()}" name="correo" class="form-control"
                                       id="correo" readonly>
                            </div>
                        </div>
                        <!-- Button -->
                        <div class="m-auto mt-5 text-center">
                            <div>
                                <button type="submit" class="btn btn-primary btn-login mb-4 mt-4"> Actualizar
                                    Información
                                </button>
                            </div>
                            <div>
                                <h6 class="text-center aste__marcados">Los elementos marcados con * no son
                                    actualizables</h6>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </main>
    </c:if>
    <!-- /.content -->
</section>

<script>


    $(document).ready(function () {
        $("#form_organizacion").validate({
            errorClass: "is-invalid",
            validClass: "is-valid",
            rules: {
                nombre: {
                    required: true
                },
                razonSocial: {
                    required: true
                },
                rfc: {
                    required: true
                },
                correo: {
                    required: true,
                    email: true
                },

            },
            messages: {
                nombre: {
                    required: "El Nombre es requerido.",
                },
                razonSocial: {
                    required: "El Apellido paterno es requerido.",
                },
                rfc: {
                    required: "El Apellido materno es requerido.",
                },
                correo: {
                    required: "El Correo es requerido.",
                    email: "El correo debe ser en el siguiente formato nombre@dominio.com."
                },

            },
            submitHandler: function (form) {
                enviarDatosEditar();
            }
        })
    })


    let enviarDatosEditar = () => {

        //Obtenemos los datos de los inputs para registrar en el backend
        let nombre = $('#nombre').val();
        let razonSocial = $('#razonSocial').val();
        let rfc = $('#rfc').val();
        let telefono = $('#telefono').val();
        let correo = $('#correo').val();



        let idOrganizacion = $('#idOrganizacion').val();
        let idUsuario = $('#idUsuario').val();

        let formData = {
            accion: 'modificar',
            nombreOrganizacion: nombre,
            razonSocial: razonSocial,
            rfc: rfc,
            correo: correo,
            telefono: telefono,
            idOrganizacion: idOrganizacion,
            idUsuario: idUsuario,
        }

        console.log("formData " + formData)
        $.ajax({
            type: "POST",
            url: "/organizacion",
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

                    $("#nombreCompleto").text(response.newGetNombreCompleto);
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Registro exitoso',
                        text: "La Organización se ha registrado correctamente.",
                        showConfirmButton: false,
                        timer: 2500
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


<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.min.js"></script>