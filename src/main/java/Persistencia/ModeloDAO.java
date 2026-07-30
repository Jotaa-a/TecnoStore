
package Persistencia;

import Modelo.Celular;
import java.util.ArrayList;

public class ModeloDAO {
    private ArrayList<Celular> celular = new ArrayList<>();
    
    public ArrayList<ModeloCelular> listar(){

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

    try(Connection con = c.conectar()){

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            Marca marca = new Marca(
                    rs.getInt("id_mks"),
                    rs.getString("marca")
            );

            Sistema_operativo sistema = new Sistema_operativo(
                    rs.getInt("id_so"),
                    rs.getString("sistema")
            );

            ModeloCelular modelo = new ModeloCelular(
                    rs.getInt("id_md"),
                    marca,
                    sistema,
                    rs.getString("modelo"),
                    Gama.valueOf(rs.getString("gama"))
            );

            respuesta.add(modelo);

        }

    }catch(SQLException e){
        e.printStackTrace();
    }

    return respuesta;
}
    
    public ModeloCelular buscar(int id) {

    ModeloCelular modelo = null;

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

    try(Connection con = c.conectar()){

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if(rs.next()){

            Marca marca = new Marca(
                    rs.getInt("id_mks"),
                    rs.getString("marca")
            );

            Sistema_operativo sistema = new Sistema_operativo(
                    rs.getInt("id_so"),
                    rs.getString("sistema")
            );

            modelo = new ModeloCelular(
                    rs.getInt("id_md"),
                    marca,
                    sistema,
                    rs.getString("modelo"),
                    Gama.valueOf(rs.getString("gama"))
            );

        }

    }catch(SQLException e){
        e.printStackTrace();
    }

    return modelo;
}
    
}
