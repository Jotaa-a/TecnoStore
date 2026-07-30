package Persistencia;

import Modelo.Celular;
import Patron.FactoryCelular;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CelularesDAO {

    Conexion c = new Conexion();
    private final FactoryCelular factory = new FactoryCelular();

    public void insert(Celular celular) {
        try (Connection con = c.conectar()) {
            String sql = "insert into celulares(id_modelo, precio, stock) values (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, celular.getModelo().getId_md());
            ps.setDouble(2, celular.getPrecio());
            ps.setInt(3, celular.getStock());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Celular celular) {
        try (Connection con = c.conectar()) {
            String sql = "update celulares set id_modelo=?, precio=?, stock=? where id_ce=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, celular.getModelo().getId_md());
            ps.setDouble(2, celular.getPrecio());
            ps.setInt(3, celular.getStock());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Celular> listar() {
        ArrayList<Celular> respuesta = new ArrayList<>();
        String sql = """
            SELECT
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

            FROM celulares c

            INNER JOIN modelos m
                ON c.id_modelo = m.id_md

            INNER JOIN marcas ma
                ON m.id_marca = ma.id_mks

            INNER JOIN sistema_operativo so
                ON m.id_so = so.id_so
            """;
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                respuesta.add(factory.crear(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }

    public Celular buscar(int id) {
        Celular celular = null;
        String sql = """
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
                     
                     where c.id_ce = ?
                     """;
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                celular = factory.crear(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return celular;
    }

    public boolean delete(Celular celular) {
        if (celular == null) {
            System.out.println("NO EXISTE EL CELULAR!");
            return false;
        }

        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("delete from celulares where id_ce=?");
            ps.setInt(1, celular.getId_celular());
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

}