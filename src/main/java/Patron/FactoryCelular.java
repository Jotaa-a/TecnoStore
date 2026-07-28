package Patron;

import Modelo.Celular;
import Modelo.Gama;
import Modelo.Marca;
import Modelo.ModeloCelular;
import Modelo.Sistema_operativo;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FactoryCelular {
    public Celular crear(ResultSet rs) throws SQLException{
       Marca marca = new Marca(
               rs.getInt("id_mks"), 
               rs.getString("marca")
       );
       
        Sistema_operativo sistema = new Sistema_operativo(
                rs.getInt("id_so"), 
                rs.getString("sistema")
        );
        
        ModeloCelular modelo = new ModeloCelular(rs.getInt(
                "id_md"), 
                marca, 
                sistema, 
                rs.getString("modelo"),
                Gama.valueOf(rs.getString("gama"))
        );
        
        return new Celular(rs.getInt("id_ce"),
                modelo,
                rs.getDouble("precio"),
                rs.getInt("stock")
        );
    }
}
