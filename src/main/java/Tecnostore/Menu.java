package Tecnostore;


import java.util.Scanner;
import Utilidades.validarEntero;

import Logica.GestorVentas;
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
                           3. Registrar ventas  
                           4. Reportes 
                           5. SALIR
                           """);
            op = vE.validarEntero("Elija una opción");
            switch (op) {
                case 1:
                    MenuCelulares menuCelulares = new MenuCelulares();
                    menuCelulares.menu();
                    break;
                case 2:
                    MenuClientes menuClientes = new MenuClientes();
                    menuClientes.menu();
                    break;
                case 3:
                    GestorVentas gestorVentas = new GestorVentas();
                    gestorVentas.registrarVenta();
                    break;
                case 4:
                    MenuReportes menuReportes = new MenuReportes();
                    menuReportes.menu();
                    break;
                case 5:
                    System.out.println("Volviendo al menur anterior");
                    break;
            }
        } while (op != 5);

    }
}