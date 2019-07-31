<%-- 
    Document   : Ventas
    Created on : 22/07/2019, 02:44:39 PM
    Author     : ammac
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Ventas</title>
    </head>
    <body>
        <div class="d-flex">
            <div class="col-sm-5">
                <div class="card">
                    <form action="Controlador?menu=Ventas" method="POST">
                        <div class="card-body">
                            <div class="form-group"> 
                                <label> Datos del Cliente</label>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-7 d-flex">
                                    <input type="text" name="codigocliente" value="${cl.getDni()}" class="form-control" placeholder="Codigo Cliente">
                                    <input type="submit" name="accion" value="BuscarCliente" class="btn btn-outline-info">
                                </div>
                                <div class="col-sm-5">
                                    <input type="text" name="nombrescliente" value="${cl.getNom()}" placeholer="Datos Cliente" class="form-control" placeholder="Nombre Cliente">
                                </div>
                            </div>
                            <div class="form-group">
                                <label> Datos Producto</label>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-7 d-flex">
                                    <input type="text" name="codigoproducto" value="${pr.getId()}" class="form-control" placeholder="Codigo">
                                    <input type="submit" name="accion" value="BuscarProducto" class="btn btn-outline-info">
                                </div>
                                <div class="col-sm-5">
                                    <input type="text" name="nombresproducto" value="${pr.getNombres()}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-4">
                                    <input type="number" name="precio" value="${pr.getPrecio()}" placeholder="Precio" class="form-control">
                                </div>
                                <div class="col-sm-4">
                                    <input type="number" name="cantidad" value=1 class="form-control">
                                </div>
                                <div class="col-sm-4">
                                    <input type="number" name="stock" value="${pr.getStock()}"placeholder="Stock" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <input type="submit" name="accion" value="Agregar" class="btn btn-outline-info">
                            </div>
                    </form>    
                </div>
              </div>
            </div>    
            <div class="col-sm-7">
                <div class="card">
                    <div class="card-body">
                        
                        <div class="d-flex col-sm-5 ml-auto">
                            <label>Nro.Serie:</label>
                            <input type="text" name="NroSerie" value="${nserie}" class="form-control">
                        </div>
                        <br>
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Nro.</th>
                                    <th>Codígo</th>
                                    <th>Descripción</th>
                                    <th>Precio</th>
                                    <th>Cantidad</th>
                                    <th>SubTotal</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="list" items="${lista}">
                                <tr>
                                    <td>${list.getItem()}</td>
                                    <td>${list.getId()}</td>
                                    <td>${list.getDescP()}</td>
                                    <td>${list.getPrecio()}</td>
                                    <td>${list.getCantidad()}</td>
                                    <td>${list.getSubtotal()}</td>
                                    <td>
                                        <input type="submit" name="accion" value="Editar" class="btn btn-warning">
                                        <input type="submit" name="accion" value="Quitar" class="btn btn-danger">
                                        
                                    </td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                        <div class="card-footer d-flex">
                            <div class="col-sm-6">
                                <a href="Controlador?menu=Ventas&&accion=Procesar" class="btn btn-success">Procesar</a>
                                <input type="submit" name="accion" value="Cancelar" class="btn btn-danger">
                            </div>
                            <div class="col-sm-3 ml-auto">
                                <input type="text" name="txtTotal" value="Q. ${tp}0" class="form-control">
                            </div>
                        </div>
                </div>  
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
