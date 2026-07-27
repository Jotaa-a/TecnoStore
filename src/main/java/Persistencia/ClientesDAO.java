package Persistencia;

import Modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientesDAO {
    Conexion c = new Conexion();
    
    public Cliente buscarPorIdentificacion(String identificaion){
        String sql = "SELECT * FROM clientes WHERE identificacion = ?";
        
        try(Connection con = c.conectar();
            PreparedStatement ps = con.prepareStatement(sql)
            ) {
            
            ps.setString(1, identificaion);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id_cl"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setIdentificacion(rs.getString("identificacion"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setCorreo(rs.getString("correo"));
                
                return cliente;
            }
            
            return null;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void insert(Cliente cliente){
        try (Connection con = c.conectar()) {
            String sql = "insert into clientes(nombre, identificacion, correo, telefono) values (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void crear(Producto p){
        try (Connection con = c.conectar()){
            String sql = ("insert into producto(nombre, descripcion, categoria_fk, stock, precio_compra, precio_venta) values (?,?,?,?,?,?)");
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setObject(3, p.getCategoria().getId());// Test
            ps.setInt(4, p.getStock());
            ps.setDouble(5, p.getPrecio_compra());
            ps.setDouble(6, p.getPrecio_venta());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
