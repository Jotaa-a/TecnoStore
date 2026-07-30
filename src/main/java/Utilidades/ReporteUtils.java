package Utilidades;

import Persistencia.Conexion;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReporteUtils {
    private Conexion c = new Conexion();    
    public void generarReporteVentas() throws IOException{
        String sql = """
            SELECT
                v.id_v,
                v.fecha,
                cl.nombre AS cliente,
                m.modelo,
                dv.cantidad,
                dv.subtotal,
                v.total
            FROM ventas v

            INNER JOIN clientes cl
                ON v.id_cliente = cl.id_cl

            INNER JOIN detalle_ventas dv
                ON v.id_v = dv.id_venta

            INNER JOIN celulares ce
                ON dv.id_celular = ce.id_ce

            INNER JOIN modelos m
                ON ce.id_modelo = m.id_md

            ORDER BY v.id_v;
            """;
        
        File carpeta = new File("Reportes");

        if (!carpeta.exists()) {
            carpeta.mkdir();
        }

        File archivo = new File(carpeta, "reporte_ventas.txt");

        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
        
        try (Connection con = c.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
         ){
            bw.write("========================================");
            bw.newLine();
            bw.write("      REPORTE GENERAL DE VENTAS");
            bw.newLine();
            bw.write("========================================");
            bw.newLine();
            bw.newLine();
            
            int ventaActual = -1;
            
            while (rs.next()) {

                if (ventaActual != rs.getInt("id_v")) {

                    ventaActual = rs.getInt("id_v");

                    bw.write("----------------------------------------");
                    bw.newLine();
                    bw.write("VENTA #" + ventaActual);
                    bw.newLine();
                    bw.write("Fecha: " + rs.getDate("fecha"));
                    bw.newLine();
                    bw.write("Cliente: " + rs.getString("cliente"));
                    bw.newLine();
                    bw.write("----------------------------------------");
                    bw.newLine();
                }

                bw.write(
                        rs.getString("modelo")
                        + " | Cantidad: " + rs.getInt("cantidad")
                        + " | Subtotal: $" + String.format("%,.0f", rs.getDouble("subtotal"))
                );

                bw.newLine();
            }
            
            bw.write("Total venta: $" + String.format("%,.0f", rs.getDouble("total")));
            bw.newLine();
            bw.newLine();

            System.out.println("Reporte generado correctamente.");

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
