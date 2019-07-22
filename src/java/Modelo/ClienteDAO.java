package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAO {
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    //Operaciones CRUD
    
    public List listar(){
        String sql="select * from cliente";
        List<Cliente>lista=new ArrayList<>();
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Cliente cl=new Cliente();
                cl.setId(rs.getInt(1));
                cl.setDni(rs.getString(2));
                cl.setNom(rs.getString(3));
                cl.setDir(rs.getString(4));
                cl.setEst(rs.getString(5));
                lista.add(cl);
                }
        } catch (Exception e){
        }
        return lista;
    }
    public int agregar(Cliente cl){
        String sql="insert into cliente(Dni, Nombres, Direccion, Estado) values (?,?,?,?)";
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNom());
            ps.setString(3, cl.getDir());
            ps.setString(4, cl.getEst());
            ps.executeUpdate();
           
        } catch (Exception e){
        } 
        return r;
    }
    public Cliente listarID(int id){
        Cliente cl=new Cliente();
        String sql="select * from cliente where IdCliente="+id;
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                cl.setDni(rs.getString(2));
                cl.setNom(rs.getString(3));
                cl.setDir(rs.getString(4));
                cl.setEst(rs.getString(5));
            }
        } catch (Exception e){
        }
        return cl;
    }
    public int actualizar(Cliente cl){
        String sql="update cliente set Dni=?, Nombres=?, Direccion=?, Estado=? where IdCliente=?";
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNom());
            ps.setString(3, cl.getDir());
            ps.setString(4, cl.getEst());
            ps.setInt(5, cl.getId());
            ps.executeUpdate();
    } catch (Exception e){
        }
        return r;
    }    
    public void eliminar(int id) {
            String sql="delete from cliente where IdCliente="+id;
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
    } catch (Exception e){
        }
    }
}
