<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="es">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <title>SIGEV | Iniciar Sesión </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/assets/css/style.css">
</head>

<body>
<div class="container">
    <div class="title-page">
        <h1 class="text-center"> SISTEMA DE GESTIÓN DE EVENTOS Y VOLUNTARIOS</h1>
    </div>
    <div class="form-signin">
        <form id="userLogin">
            <img src="/assets/img/Logo SIGEV.JPG" href="/index.jsp" width="72px" alt="Logo">
            <h1 class="h3 mb-3 mt-3 mb-4">Iniciar sesión</h1>
            <div class="div-msg-error" style="display: none">
                <p id="msg-error" class="mt-0 mb-0"></p>
            </div>
            <div class="mt-3 text-start">
                <label for="correo" class="form-label">Correo electrónico</label>
                <input type="email" class="form-control my-style-input" name="correo" id="correo"
                       placeholder="correo@gmail.com">
            </div>

            <div class="mt-2 text-start">
                <label for="contrasenia" class="form-label">Contraseña</label>
                <input type="password" class="form-control" name="contrasenia" id="contrasenia" placeholder="********">
            </div>
            <button type="submit" class="w-100 btn btn-primary mt-4">
                <span id="txt-btn">Iniciar Sesión</span>

                <div id="loading" style="display: none">
                    <span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
                    <span class="sr-only">Cargando...</span>
                </div>


            </button>
            <div class="col-12">
                <label for="contrasenia" class="form-label menText text-end mt-3">Olvidaste tu contraseña?<a
                        href="jsp/seguridad/restablecerContra.jsp"> Recuperala</a></label>
            </div>
            <div class="checkbox mb-3 mt-3 ">
                <label>
                    <input type="checkbox" value="remember-me"> Recordarme
                </label>
            </div>
            <div class="col-12 col-sm-12 col-xl-12 ">
                <p class="mt-2">¿No tienes cuenta? <a href="jsp/seguridad/registro.jsp">Registrate</a></p>
            </div>
            <p class="mt-5 mb-3 text-muted"> 2023 © SIGEV</p>
        </form>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.min.js"></script>


<script>
    $(document).ready(function () {

        $("#userLogin").validate({
            errorClass: "is-invalid",
            validClass: "is-valid",
            rules: {
                correo: {
                    required: true,
                    email: true
                },
                contrasenia: {
                    required: true,
                }
            },
            messages: {
                correo: {
                    required: "El correo es requerido.",
                    email: "El correo electrónico debe ser en el siguiente formato name@domain.com."
                },
                contrasenia: "La contraseña es requerida.",
            },
            errorLabelContainer: "#messageBox",
            submitHandler: function (form) {
                // do other things for a valid form
                sendData();
            }
        })
        const sendData = () => {
            let loading = true;
            $("#loading").show();
            $("#txt-btn").hide();


            var correo = document.getElementById("correo").value;
            var contrasenia = document.getElementById("contrasenia").value;

            // Objeto con los datos del formulario
            var formData = {
                correo: correo,
                contrasenia: contrasenia
            };

            // Realizar la petición AJAX
            $.ajax({
                type: "POST",
                url: "/user-servlet",
                data: formData,
                success: function (response) {
                    loading = false;

                    // Procesar la respuesta del servlet si es necesario
                    console.log("Respuesta del servidor:", response);


                    $("#loading").hide();
                    $("#txt-btn").show();

                    if(response.error){
                        $(".div-msg-error").show();
                        $('#msg-error').text(response.message);
                    }else {

                        switch (response.tipoSesion){
                            case "Administrador":
                                window.location.href = '../dashboard/index.jsp';
                                break;
                            case "Organización":
                                window.location.href = 'dashboard/dashboardOrganizacion.jsp';
                                break;
                            case "Voluntario":
                                window.location.href = 'dashboard/dashboardVoluntario.jsp';
                                break;
                        }
                    }

                   /* if (response.status === 'success') {

                        /*setTimeout(() => {
                                window.location.href = "dashboard";
                            },
                            1000)
                        //  $("#loading").hide();

                    } else {

                    } */
                },
                error: function (error) {
                    loading = false;
                    console.error("Error en la petición AJAX:", error);
                }
            });
        }
    })

</script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>