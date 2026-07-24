package Utilidades;

import java.util.Scanner;

public class validarEntero {

    private final Scanner sc = new Scanner(System.in);

    public int validarEntero(String mensaje) {
        
        while (true) {
            try {
                System.out.print(mensaje+": ");
                int dato = Integer.parseInt(sc.nextLine());

                if (dato > 0) {
                    return dato;
                }
                
                System.out.println("Debe ingrear un número positivo");
                
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero entero.");
            }
        } 
    }

}
