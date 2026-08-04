
package Logica;
import Modelo.TopVendidos;
import Persistencia.ReportesDAO;
public class GestorReportes {
    private ReportesDAO rDAO = new ReportesDAO();

    public void stockBajo(){
        rDAO.stockBajo().stream().forEach(c -> System.out.println(
                c.getModelo().getNombre() + 
                " - Stock: "+ c.getStock()
        ));
    }
    
    public void topVentas(){
        for (TopVendidos tV : rDAO.topVentas()){
            System.out.println(tV);
        }
    }
    
    public void ventasPorMes(){
        rDAO.ventaMes()
                .stream()
                .forEach(System.out::println);
    }
}
