<link href="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.css" rel="stylesheet">

<section class="content-header">
    <div class="container-fluid">
        <div class="row mb-2">
            <div class="col-sm-6">
                <h1>Usuarios</h1>
            </div>
            <div class="col-sm-6">
                <ol class="breadcrumb float-sm-right">
                    <li class="breadcrumb-item"><a href="#">Home</a></li>
                    <li class="breadcrumb-item active">DataTables</li>
                </ol>
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
                                <th>Nombre</th>
                                <th>Primer Apellido</th>
                                <th>Segundo Apellido</th>
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
                { "data": "idPersona" },
                { "data": "nombrePersona" },
                { "data": "primerApellido" },
                { "data": "segundoApellido" },
                {"defaultContent": "<a href='#'> <i class='fa fa-pen''></i> </a>"}

            ]

        });



    });
</script>


<script src="https://cdn.datatables.net/v/bs5/dt-1.13.6/datatables.min.js"></script>