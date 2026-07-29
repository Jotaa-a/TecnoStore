
package Persistencia;

import Modelo.Celular;
import Modelo.TopVendidos;
import Modelo.VentasMes;
import Patron.FactoryCelular;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReportesDAO {
    
    private static Conexion c = new Conexion();
    private final FactoryCelular factory = new FactoryCelular();
    
    public ArrayList<Celular> stockBajo() {
        ArrayList<Celular> respuesta = new ArrayList<>();
        String sqlStock = """
                     select
                         c.id_ce,
                         c.precio,
                         c.stock,
                     
                         m.id_md,
                         m.modelo,
                         m.gama,
                     
                         ma.id_mks,
                         ma.nombre AS marca,
                     
                         so.id_so,
                         so.nombre AS sistema
                     
                     from celulares c
                     
                     inner join modelos m
                         ON c.id_modelo = m.id_md
                     
                     inner join marcas ma
                         ON m.id_marca = ma.id_mks
                     
                     inner join sistema_operativo so
                         ON m.id_so = so.id_so
                     
                     where c.stock <= 3
                     """;
        try (Connection con = c.conectar()){
            PreparedStatement psStock = con.prepareStatement(sqlStock);
            ResultSet rs = psStock.executeQuery();
            while (rs.next()) {                
                respuesta.add(factory.crear(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return respuesta;
    }
    
    public ArrayList<TopVendidos> topVentas(){
        ArrayList<TopVendidos> respuesta = new ArrayList<>();
        String slqTop = """
                        select 
                            m.modelo as Modelo,
                            sum(dv.cantidad) as Vendidos
                        from detalle_ventas dv
                        
                        inner join celulares c
                        on dv.id_celular = c.id_ce
                        
                        inner join modelos m
                        on m.id_md = c.id_modelo
                        
                        group by c.id_ce, m.modelo
                        
                        order by Vendidos desc
                        
                        limit 3
                        """;
        try (Connection con = c.conectar()){
            PreparedStatement psTop = con.prepareStatement(slqTop);
            ResultSet rs = psTop.executeQuery();
            while (rs.next()) {                
                respuesta.add(new TopVendidos(rs.getString("Modelo"), rs.getInt("Vendidos")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return respuesta;
    }
    
    public ArrayList<VentasMes> ventaMes(){
        ArrayList<VentasMes> respuesta = new ArrayList<>();
        String sqlVenta = """
                            select 
                                monthname(fecha) as mes,
                                sum(total) as total
                            from ventas 
                            group by month(fecha), monthname(fecha)
                            order by month(fecha)
                          """;
        try (Connection con = c.conectar()){
            PreparedStatement psVenta = con.prepareStatement(sqlVenta);
            ResultSet rs = psVenta.executeQuery();
            while (rs.next()) {                
                respuesta.add(new VentasMes(rs.getString("mes"), rs.getDouble("total")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return respuesta;
    }
}
