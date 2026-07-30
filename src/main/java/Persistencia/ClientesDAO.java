package Persistencia;

import Modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClientesDAO {

    Conexion c = new Conexion();

    public Cliente buscarPorIdentificacion(String identificaion) {
        String sql = "SELECT * FROM clientes WHERE identificacion = ?";

        try (Connection con = c.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, identificaion);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
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

    public void insert(Cliente cliente) {
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("insert into clientes(nombre, identificacion, correo, telefono) values (?,?,?,?)");
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getIdentificacion());
            ps.setString(3, cliente.getCorreo());
            ps.setString(4, cliente.getTelefono());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Cliente cliente) {
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("update clientes set nombre=?, identificacion=?, correo=?, telefono=? where  id_cl=?");
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getIdentificacion());
            ps.setString(3, cliente.getCorreo());
            ps.setString(4, cliente.getTelefono());
            ps.setInt(5, cliente.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Cliente> listar() {
        ArrayList<Cliente> respuesta = new ArrayList<>();
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("select * from clientes");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                respuesta.add(new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }

    public Cliente buscar(int id) {
        Cliente cliente = null;

        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("select * from clientes where id_cl = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cliente = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return cliente;
    }

    public boolean delete(Cliente cliente) {
        if (cliente == null) {
            System.out.println("EL CLIENTE NO EXISTE");
            return false;
        }
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("delete from clientes where id_cl=?");
            ps.setInt(1, cliente.getId());
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

}
