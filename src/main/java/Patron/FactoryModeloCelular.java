package Patron;

import Modelo.Gama;
import Modelo.Marca;
import Modelo.ModeloCelular;
import Modelo.Sistema_operativo;
import java.sql.ResultSet;
import java.sql.SQLException;




public class FactoryModeloCelular {
    public ModeloCelular crear(ResultSet rs) throws SQLException {

    Marca marca = new Marca(
            rs.getInt("id_mks"),
            rs.getString("marca")
    );

    Sistema_operativo sistema = new Sistema_operativo(
            rs.getInt("id_so"),
            rs.getString("sistema")
    );

    return new ModeloCelular(
            rs.getInt("id_md"),
            marca,
            sistema,
            rs.getString("modelo"),
            Gama.valueOf(rs.getString("gama"))
    );
}
}