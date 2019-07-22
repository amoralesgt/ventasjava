package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO {
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public Usuario validar(String user, String dni){
        Usuario us=new Usuario();
        String sql="select * from empleado where User=? and Dni=?";
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, dni);
            rs=ps.executeQuery();
            while (rs.next()) {
                us.setId(rs.getInt("IdEmpleado"));
                us.setUser(rs.getString("User"));
                us.setDni(rs.getString("Dni"));
                us.setNom(rs.getString("Nombres"));
        }
        } catch (Exception e){
        }
        return us;
    }
    
    //Operaciones CRUD
    
    public List listar(){
        String sql="select * from empleado";
        List<Usuario>lista=new ArrayList<>();
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Usuario us=new Usuario();
                us.setId(rs.getInt(1));
                us.setDni(rs.getString(2));
                us.setNom(rs.getString(3));
                us.setTel(rs.getString(4));
                us.setEstado(rs.getString(5));
                us.setUser(rs.getString(6));
                lista.add(us);
                }
        } catch (Exception e){
        }
        return lista;
    }
    public int agregar(Usuario us){
        String sql="insert into empleado(Dni, Nombres, Telefono, Estado, User) values (?,?,?,?,?)";
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, us.getDni());
            ps.setString(2, us.getNom());
            ps.setString(3, us.getTel());
            ps.setString(4, us.getEstado());
            ps.setString(5, us.getUser());
            ps.executeUpdate();
           
        } catch (Exception e){
        } 
        return r;
    }
    public Usuario listarID(int id){
        Usuario us=new Usuario();
        String sql="select * from empleado where IdEmpleado="+id;
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                us.setDni(rs.getString(2));
                us.setNom(rs.getString(3));
                us.setTel(rs.getString(4));
                us.setEstado(rs.getString(5));
                us.setUser(rs.getString(6));
            }
        } catch (Exception e){
        }
        return us;
    }
    public int actualizar(Usuario us){
        String sql="update empleado set Dni=?, Nombres=?, Telefono=?, Estado=?, User=? where IdEmpleado=?";
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, us.getDni());
            ps.setString(2, us.getNom());
            ps.setString(3, us.getTel());
            ps.setString(4, us.getEstado());
            ps.setString(5, us.getUser());
            ps.setInt(6, us.getId());
            ps.executeUpdate();
    } catch (Exception e){
        }
        return r;
    }    
    public void eliminar(int id) {
            String sql="delete from empleado where IdEmpleado="+id;
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
    } catch (Exception e){
        }
    }
}
