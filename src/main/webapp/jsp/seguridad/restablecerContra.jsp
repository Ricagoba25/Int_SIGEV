<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="es">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Inicio Sesión</title>
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/css/styles.css" rel="stylesheet">

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-xl-12" style="margin-bottom: 150px">
            <h1 class="titulo text-center">SISTEMA DE GESTIÓN DE EVENTOS Y VOLUNTARIOS</h1>
        </div>
        <div class="text-center d-flex justify-content-center align-items-center">
            <div class="login">

                <form id="form_enviarCorreo">

                    <div class="row d-flex justify-content-center align-items-center">
                        <div class="col-xl-12">
                            <img src="/assets/img/Logo%20SIGEV.JPG" alt="LOGO" class="logo">
                        </div>
                        <div class="col-10 col-sm-10 col-xl-10">
                            <div class="mb-3  mt-4 text-center">
                                <label>Ingresa tu email, se enviará un código para restablecer tu contraseña:</label>
                            </div>
                        </div>

                        <div class="col-10 col-sm-10 col-xl-10">
                            <div class="msg-error" style="display: none">
                                <span class="text-error"> No cuenta con ninguna cuenta asociada. </span>
                            </div>
                            <div class="text-start">
                                <div class="col-12">
                                    <label for="pass" class="form-label">Correo:</label>
                                </div>
                                <input type="text" class="form-control" id="pass" placeholder="Correo"
                                       name="correo">
                            </div>
                        </div>
                        <div class="col-12 col-sm-12 col-xl-12 ">
                            <button class="btn btn-login btn-primary mt-4" type="submit">Enviar Código</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<script>

    $(document).ready(function () {


        $("#form_enviarCorreo").validate({
            errorClass: "is-invalid",
            validClass: "is-valid",
            rules: {
                correo: {
                    required: true,
                    email: true
                }
            },
            messages: {
                correo: {
                    required: "El correo es requerido.",
                    email: "El correo electrónico debe ser en el siguiente formato nombre@dominio.com."
                },
            },
            submitHandler: function (form) {
                // do other things for a valid form
                sendData();
            }
        })
    });

    let sendData = () => {
        let loading = true;
        // Realizar la petición AJAX
        $.ajax({
            type: "POST",
            url: "/reset-password",
            data: formData,
            success: function (response) {
                loading = false;

                // Procesar la respuesta del servlet si es necesario
                console.log("Respuesta del servidor:", response);

                $("#loading").hide();
                $("#txt-btn").show();

                if (response.error) {
                    $(".div-msg-error").show();
                    $('#msg-error').text(response.message);
                } else {
                    window.location.href = '../dashboard/index.jsp';
                }
            },
            error: function (error) {
                loading = false;
                console.error("Error en la petición AJAX:", error);
            }
        });

    }

</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.min.js"></script>
</body>
</html>


<style>

    .msg-error {
        padding: 20px 20px;
    }

    #form_enviarCorreo div div .is-invalid, .text-error {
        color: red;
        font-size: 15px;
        margin-top: 5px;
    }

</style>