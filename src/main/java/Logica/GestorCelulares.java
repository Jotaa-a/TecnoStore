package Logica;

import Modelo.Celular;
import Modelo.TopVendidos;
import Persistencia.ReportesDAO;

public class GestorCelulares {

    private final ReportesDAO rDAO = new ReportesDAO();

    public void stockCelulares() {
        rDAO.stockBajo().stream().forEach(c -> System.out.println(
                c.getModelo().getNombre() + 
                " - Stock: "+ c.getStock()
        ));
    }
    
    public void topVendidos(){
        for (TopVendidos tV : rDAO.topVentas()){
            System.out.println(tV);
        }
    }
}
