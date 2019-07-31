
package Controlador;

import Config.GenerarSerie;
import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import Modelo.Usuario;
import Modelo.UsuarioDAO;
import Modelo.Venta;
import Modelo.VentaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ammac
 */
public class Controlador extends HttpServlet {
    
    // Variables de Usuario
    Usuario us=new Usuario();
    UsuarioDAO udao=new UsuarioDAO();
    int idu;
    
    // Variables de Producto
    Producto pr=new Producto();
    ProductoDAO pdao=new ProductoDAO();
    int idp;
    double precio;
    int stock;
    double precio1;
    int stock1;
    
    // Variables de Cliente
    
    Cliente cl=new Cliente();
    ClienteDAO cdao=new ClienteDAO();
    int idc;
    
    // Variables de Venta
    
    Venta ve=new Venta();
    List<Venta>lista=new ArrayList<>();
    int item;
    int cod;
    String desc;
    double prec;
    int cant;
    double subtotal;
    double totalPagar;
    
    String numeroserie;
    VentaDAO vdao=new VentaDAO();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String menu=request.getParameter("menu");
            String accion=request.getParameter("accion");
            if(menu.equals("Principal")){
                request.getRequestDispatcher("Principal.jsp").forward(request, response);
            }
            if(menu.equals("Producto")){
                switch(accion){                    
                    case "Listar":
                        List lista=pdao.listar();
                        request.setAttribute("productos", lista);
                        break;
                    case "Agregar":
                        stock=Integer.parseInt(request.getParameter("txtStock"));
                        precio=Double.parseDouble(request.getParameter("txtPrecio"));
                        String nombre=request.getParameter("txtNombres");
                        String estado=request.getParameter("txtEstado");
                        pr.setNombres(nombre);
                        pr.setPrecio(precio);
                        pr.setStock(stock);
                        pr.setEstado(estado);
                        pdao.agregar(pr);
                        request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                        break;
                    case "Editar":
                        idp=Integer.parseInt(request.getParameter("id"));
                        Producto p=pdao.listarID(idp);
                        request.setAttribute("producto", p);
                        request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                        break;
                    case "Actualizar":
                        stock1=Integer.parseInt(request.getParameter("txtStock"));
                        precio1=Double.parseDouble(request.getParameter("txtPrecio"));
                        String nombres=request.getParameter("txtNombres");
                        String estados=request.getParameter("txtEstados");
                        pr.setNombres(nombres);
                        pr.setPrecio(precio1);
                        pr.setStock(stock1);
                        pr.setEstado(estados);
                        pr.setId(idp);
                        pdao.actualizar(pr);
                        request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                        break;
                    case "Eliminar":
                        idp=Integer.parseInt(request.getParameter("id"));
                        pdao.eliminar(idp);
                        request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                        break;    
                    default:
                        throw new AssertionError();
                }
                request.getRequestDispatcher("Productos.jsp").forward(request, response);
            }
            if(menu.equals("Cliente")){
                switch(accion){
                    case "Listar":
                        List lista=cdao.listar();
                        request.setAttribute("clientes", lista);
                        break;
                    case "Agregar":
                        String nit=request.getParameter("txtNit");
                        String nom=request.getParameter("txtNom");
                        String dir=request.getParameter("txtDir");
                        String est=request.getParameter("txtEst");
                        cl.setDni(nit);
                        cl.setNom(nom);
                        cl.setDir(dir);
                        cl.setEst(est);
                        cdao.agregar(cl);
                        request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                        break;
                    case "Editar":
                        idc=Integer.parseInt(request.getParameter("id"));
                        Cliente c=cdao.listarID(idc);
                        request.setAttribute("cliente", c);
                        request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                        break;
                    case "Actualizar":
                        String nit1=request.getParameter("txtNit");
                        String nom1=request.getParameter("txtNom");
                        String dir1=request.getParameter("txtDir");
                        String est1=request.getParameter("txtEst");
                        cl.setDni(nit1);
                        cl.setNom(nom1);
                        cl.setDir(dir1);
                        cl.setEst(est1);
                        cl.setId(idc);
                        cdao.actualizar(cl);
                        request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                        break;
                    case "Eliminar":
                        idc=Integer.parseInt(request.getParameter("id"));
                        cdao.eliminar(idc);
                        request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                        break;    
                    default:
                        throw new AssertionError();
                }
                request.getRequestDispatcher("Clientes.jsp").forward(request, response);
            }
            if(menu.equals("Ventas")){
                    switch(accion){
                        case "BuscarCliente":
                            String dni=request.getParameter("codigocliente");
                            cl.setDni(dni);
                            cl=cdao.buscar(dni);
                            request.setAttribute("cl", cl);
                        break;
                        case "BuscarProducto":
                            int id=Integer.parseInt(request.getParameter("codigoproducto")); 
                            pr.setId(id);
                            pr=pdao.buscar(id);
                            request.setAttribute("pr", pr);
                            request.setAttribute("lista", lista);
                            request.setAttribute("tp", totalPagar);
                            request.setAttribute("cl", cl);
                        break;
                        case "Agregar":
                            request.setAttribute("cl", cl);
                            totalPagar = 0.0;
                            item = item+1;
                            cod = pr.getId();
                            desc=request.getParameter("nombresproducto");
                            prec = Double.parseDouble(request.getParameter("precio"));
                            cant = Integer.parseInt(request.getParameter("cantidad"));
                            subtotal = prec*cant;
                            ve=new Venta();
                            ve.setItem(item);
                            ve.setIdproducto(cod);
                            ve.setDescP(desc);
                            ve.setPrecio(prec);
                            ve.setCantidad(cant);
                            ve.setSubtotal(subtotal);
                            lista.add(ve);
                            for(int i = 0; i< lista.size(); i++){
                                totalPagar=totalPagar +lista.get(i).getSubtotal();
                            }
                            request.setAttribute("tp", totalPagar);
                            request.setAttribute("lista", lista);
                        break;
                        case "Procesar":
                            //Guardar la Venta
                            ve.setIdcliente(cl.getId());
                            ve.setIdusuario(4);
                            ve.setNumserie(numeroserie);
                            ve.setFecha("2019-07-29");
                            ve.setMonto(totalPagar);
                            ve.setEstado("1");
                            vdao.guardarVenta(ve);
                            //Guardar detalle de Venta
                            int idv=Integer.parseInt(vdao.IdVentas());
                            for (int i =0; i < lista.size(); i++){
                                ve=new Venta();
                                ve.setId(idv);
                                ve.setIdproducto(lista.get(i).getIdproducto());
                                ve.setCantidad(lista.get(i).getCantidad());
                                ve.setPrecio(lista.get(i).getPrecio());
                                vdao.guardarDetalleventa(ve);                              
                            }                         
                        break;  
                        default:
                            numeroserie=vdao.GenerarSerie();
                            if(numeroserie==null){
                                numeroserie="00000001";
                                request.setAttribute("nserie",numeroserie);
                            }
                            else{
                                int incrementar=Integer.parseInt(numeroserie);
                                GenerarSerie gs=new GenerarSerie();
                                numeroserie=gs.NumeroSerie(incrementar);
                                request.setAttribute("nserie", numeroserie);
                            }
                            request.getRequestDispatcher("Ventas.jsp").forward(request, response);
                     }
                request.getRequestDispatcher("Ventas.jsp").forward(request, response);
            }
            if(menu.equals("Usuario")){
                switch(accion){
                    case "Listar":
                        List lista=udao.listar();
                        request.setAttribute("usuarios", lista);
                        break;
                    case "Agregar":
                        String nit=request.getParameter("txtNit");
                        String nom=request.getParameter("txtNom");
                        String tel=request.getParameter("txtTel");
                        String est=request.getParameter("txtEst");
                        String usu=request.getParameter("txtUsu");
                        us.setDni(nit);
                        us.setNom(nom);
                        us.setTel(tel);
                        us.setEstado(est);
                        us.setUser(usu);
                        udao.agregar(us);
                        request.getRequestDispatcher("Controlador?menu=Usuario&accion=Listar").forward(request, response);
                        break;
                    case "Editar":
                        idu=Integer.parseInt(request.getParameter("id"));
                        Usuario u=udao.listarID(idu);
                        request.setAttribute("usuario", u);
                        request.getRequestDispatcher("Controlador?menu=Usuario&accion=Listar").forward(request, response);
                        break;
                    case "Actualizar":
                        String nit1=request.getParameter("txtNit");
                        String nom1=request.getParameter("txtNom");
                        String tel1=request.getParameter("txtTel");
                        String est1=request.getParameter("txtEst");
                        String usu1=request.getParameter("txtUsu");
                        us.setDni(nit1);
                        us.setNom(nom1);
                        us.setTel(tel1);
                        us.setEstado(est1);
                        us.setUser(usu1);
                        us.setId(idu);
                        udao.actualizar(us);
                        request.getRequestDispatcher("Controlador?menu=Usuario&accion=Listar").forward(request, response);
                        break;
                    case "Eliminar":
                        idu=Integer.parseInt(request.getParameter("id"));
                        udao.eliminar(idu);
                        request.getRequestDispatcher("Controlador?menu=Usuario&accion=Listar").forward(request, response);
                        break;    
                    default:
                        throw new AssertionError();
                }
                request.getRequestDispatcher("Usuarios.jsp").forward(request, response);
            }   
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controlador</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controlador at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
