package Tecnostore;

import Logica.GestorCelulares;
import Logica.GestorVentas;
import Modelo.Cliente;
import Persistencia.Conexion;

public class TecnoStore {

    public static void main(String[] args) {
        Cliente cliente = new Cliente(0, "pedro", "1551120", "pedro@gmail.com", "123546");
        
        //GestorVentas gs = new GestorVentas();
        //gs.registrarVenta();
        
        GestorCelulares gc = new GestorCelulares();
        gc.stockCelulares();
        gc.topVendidos();
    }
}
