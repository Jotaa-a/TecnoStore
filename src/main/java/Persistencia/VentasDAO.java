package Persistencia;

import Logica.ItemVenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Modelo.Cliente;
import java.sql.SQLException;
import java.util.ArrayList;

public class VentasDAO {

    Conexion c = new Conexion();

    public boolean registrarVenta(Cliente cliente, ArrayList<ItemVenta> carrito, double total) {
        try (Connection con = c.conectar()) {

            con.setAutoCommit(false);
            
            try (
                PreparedStatement psVenta = con.prepareStatement("insert into ventas(id_cliente, total) values(?,?)", java.sql.Statement.RETURN_GENERATED_KEYS); 
                PreparedStatement psDetalle = con.prepareStatement("insert into detalle_ventas(id_venta, id_celular, cantidad, subtotal) values (?,?,?,?)"); 
                PreparedStatement psStock = con.prepareStatement("update celulares set stock=? where id_ce=?");
            ){
                psVenta.setInt(1, cliente.getId());
                psVenta.setDouble(2, total);
                psVenta.executeUpdate();

                ResultSet rs = psVenta.getGeneratedKeys();
                if (!rs.next()) {
                    throw new SQLException("No se pudo obtener el ID de la venta.");
                }
                int idVenta = rs.getInt(1);

                for (ItemVenta item : carrito) {
                    psDetalle.setInt(1, idVenta);
                    psDetalle.setInt(2, item.getCelular().getId_celular());
                    psDetalle.setInt(3, item.getCantidad());
                    psDetalle.setDouble(4, item.getSubtotal());

                    psDetalle.executeUpdate();

                    int nuevoStock = item.getCelular().getStock() - item.getCantidad();
                    psStock.setInt(1, nuevoStock);
                    psStock.setInt(2, item.getCelular().getId_celular());

                    psStock.executeUpdate();

                    item.getCelular().setStock(nuevoStock);

                }

                con.commit();
                return true;
            
            } catch (SQLException e) {
                con.rollback();
                throw e;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        
    }

}
