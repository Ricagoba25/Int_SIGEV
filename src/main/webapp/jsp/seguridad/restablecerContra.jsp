<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="es">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Inicio Sesión</title>
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/css/styles.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-xl-12" style="margin-bottom: 150px">
            <h1 class="titulo text-center">SISTEMA DE GESTIÓN DE EVENTOS Y VOLUNTARIOS</h1>
        </div>
        <div class="text-center d-flex justify-content-center align-items-center">
            <div class="login">
                <form action="envioCodigo.jsp" method="get">
                <div class="row d-flex justify-content-center align-items-center">
                    <div class="col-xl-12 mb-4">
                        <img src="/assets/img/Logo%20SIGEV.JPG" alt="LOGO" class="logo">
                    </div>
                    <div class="col-10 col-sm-10 col-xl-10">
                        <div class="mb-3 text-center" style="margin-bottom: 20px">
                            <label>Ingresa tu email, se enviará un código para restablecer tu contraseña:</label>
                        </div>
                    </div>

                    <div class="col-10 col-sm-10 col-xl-10">
                        <div class="text-start">
                            <div class="col-12">
                                <label for="pass" class="form-label">Correo:</label>
                            </div>
                            <input type="text" class="form-control" id="pass" placeholder="Correo" style="margin-bottom: 30px">
                        </div>
                        </div>
                        <div class="col-12 col-sm-12 col-xl-12 " style="margin-top: 30px">
                            <button class="btn btn-login btn-primary mt-4" type="submit">Enviar Código</button>
                        </div>
                    </div>
                </form>

                </div>
            </div>
        </div>
    </div>


    <script src="../../js/bootstrap.min.js"></script>
</body>
</html>