package Tecnostore;

import Logica.GestorCelulares;
import java.util.Scanner;
import Utilidades.validarEntero;

public class Menu {

    private final Scanner sc = new Scanner(System.in);
    private validarEntero vE = new validarEntero();

    public void iniciar() {
        int op = 0;
        do {

            System.out.println("========== BIENVENIDO A TECNOSTORE =========");
            System.out.println("""
                           1. Gestion de celulares 
                           2. Gestion de clientes 
                           3. Registrar venta  
                           4. Reportes 
                           5. SALIR
                           """);
            op = vE.validarEntero("Elija una opción");
            switch (op) {
                case 1:
                    GestorCelulares gc = new GestorCelulares();
                    gc.stockCelulares();
                    gc.topVendidos();
                    break;
                
            }
        } while (op < 5);

    }
}
