package Persistencia;

import Modelo.Celular;
import Modelo.Gama;
import Modelo.Marca;
import Modelo.ModeloCelular;
import Modelo.Sistema_operativo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Patron.FactoryModeloCelular;

public class ModeloDAO {

    private ArrayList<Celular> celular = new ArrayList<>();
    private Conexion c = new Conexion();

    private FactoryModeloCelular factory = new FactoryModeloCelular();
    
    public void update(ModeloCelular modelo) {
        try (Connection con = c.conectar()) {
            String sql = "update modelos set id_marca=?, id_so=?, modelo=?, gama=? where id_md=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, modelo.getId_md());
            ps.setDouble(2, modelo.getMarca().getId_mk());
            ps.setInt(3, modelo.getSo().getId_so());
            ps.setString(4, modelo.getNombre());
            ps.setObject(5, modelo.getGama());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<ModeloCelular> listar() {

        ArrayList<ModeloCelular> respuesta = new ArrayList<>();

        String sql = """
        SELECT
            m.id_md,
            m.modelo,
            m.gama,

            ma.id_mks,
            ma.nombre AS marca,

            so.id_so,
            so.nombre AS sistema

        FROM modelos m

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
            e.printStackTrace();
        }

        return respuesta;
    }

    public ModeloCelular buscar(int id) {

        String sql = """
        SELECT
            m.id_md,
            m.modelo,
            m.gama,

            ma.id_mks,
            ma.nombre AS marca,

            so.id_so,
            so.nombre AS sistema

        FROM modelos m

        INNER JOIN marcas ma
            ON m.id_marca = ma.id_mks

        INNER JOIN sistema_operativo so
            ON m.id_so = so.id_so

        WHERE m.id_md = ?
        """;

        try (Connection con = c.conectar()) {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return factory.crear(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}