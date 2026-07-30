
package Tecnostore;

import Logica.GestorClientes;
import Utilidades.validarEntero;


public class MenuClientes {
    
    private GestorClientes gestor = new GestorClientes();
    private validarEntero vInt = new validarEntero();
     public void menu(){
        int opcion;
        
        do {
            System.out.println("""
                               ===== GESTION DE CELULARES =====
                               1. Registrar cliente
                               2. Actualizar cliente
                               3. Eliminar cliente
                               4. Listas cliente
                               5. salir
                               ================================
                               """);
            opcion = vInt.validarEntero("Seleccione una opcion");
            
            switch (opcion) {
                case 1:
                    gestor.registrarCLiente();
                    break;
                case 2:
                    gestor.actualizarCliente();
                    break;
                case 3:
                    gestor.eliminarCliente();
                    break;
                case 4:
                    gestor.listarClientes();
                    break;
                case 5:
                     System.out.println("Regresando al menú principal...");
                    break;
            }
        } while (opcion != 5);
    }
}