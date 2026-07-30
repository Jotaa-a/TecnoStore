
package Tecnostore;
import Logica.GestorCelulares;
import Utilidades.*;

public class MenuCelulares {
    
    private validarEntero vInt = new validarEntero();
    private GestorCelulares gestor = new GestorCelulares();
            
    public void menu(){
        int opcion;
        
        do {
            System.out.println("""
                               ===== GESTION DE CELULARES =====
                               1. Registrar dispositivo
                               2. Actualizar dispositivo
                               3. Eliminar dispositivo
                               4. Listas dispositivos
                               5. Stocks bajo
                               6. Top dispositivos vendidos
                               7. salir
                               ================================
                               """);
            opcion = vInt.validarEntero("Seleccione una opcion");
            
            switch (opcion) {
                case 1:
                    gestor.insertCelular();
                    break;
                case 2:
                    gestor.actualizarCelular();
                    break;
                case 3:
                    gestor.eliminarCelular();
                    break;
                case 4:
                    gestor.listarCelulares();
                    break;
                case 5:
                    gestor.stockCelulares();
                    break;
                case 6:
                    gestor.topVendidos();
                    break;
                case 7:
                     System.out.println("Regresando al menú principal...");
                    break;
                default:
                     System.out.println("Opción inválida.");
            }
        } while (opcion != 7);
    }
}
