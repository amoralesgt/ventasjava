<%-- 
    Document   : index
    Created on : 10/07/2019, 08:25:42 AM
    Author     : ammac
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Ingreso a Sistema</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-info">
  <a class="navbar-brand" href="#">Inicio</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="Controlador?menu=Producto&accion=Listar" target="myFrame">Productos<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="Controlador?menu=Cliente&accion=Listar" target="myFrame">Clientes</a>
      </li>
      <li class="nav-item">
        <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="Controlador?menu=Usuario&accion=Listar" target="myFrame">Usuarios</a>
      </li>
      <li class="nav-item">
        <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="Controlador?menu=Ventas" target="myFrame">Ventas</a>
      </li>
    </ul>     
    </div>
        <div class="dropdown">
            <button class="btn btn-outline-light dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
            ${usuario.getNom()}
         </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <a class="dropdown-item" href="#">
                    <img src="img/user.png" alt="60" width="60"/>
                </a>
              <a class="dropdown-item" href="#">${usuario.getUser()}</a>
              <a class="dropdown-item" href="#">usuario@gmail.com</a>
              <div class="dropdown-divider"></div>
              <form action="Validar" method="POST">
                  <button name="accion" value="Salir" class="dropdown-item" href="#">Salir</button>
              </form>
        </div>
  </div>
</nav>
              <div class="m-4" style="height: 550px;">
                  <iframe name="myFrame" style="height: 100%; width: 100%; border: none"></iframe>
              </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    </body>
</html>
