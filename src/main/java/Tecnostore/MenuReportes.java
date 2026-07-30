package Tecnostore;
import Utilidades.validarEntero;
import Logica.GestorReportes;
public class MenuReportes {
    
    private validarEntero vInt = new validarEntero();
    private final GestorReportes gestor = new GestorReportes();
    
    public void menu(){
        int opcion = 0;
        do {
            System.out.println("""
                               ===== REPORTES =====
                               1. Stock bajo
                               2. Top ventas
                               3. Ventas por mes
                               4. salir
                               """);
            opcion = vInt.validarEntero("Seleccione una opcion");
            
            switch (opcion) {
                case 1:
                    gestor.stockBajo();
                    break;
                case 2:
                    gestor.topVentas();
                    break;
                case 3: 
                    gestor.ventasPorMes();
                    break;
                case 4:
                    System.out.println("Volviendo al menu anterior");
                    break;
            }
        } while (opcion != 4);
    }
}
