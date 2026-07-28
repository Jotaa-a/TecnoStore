package Utilidades;

import java.util.Scanner;

public class validarChar {

    private static final Scanner sc = new Scanner(System.in);

    public char validarSN(String mensaje) {
        while (true) {
            System.out.println(mensaje);
            
            String entrada = sc.nextLine().trim().toUpperCase();
            
            if (entrada.length() == 1) {
                char respuesta = entrada.charAt(0);
                
                if (respuesta == 'S' || respuesta == 'N') {
                    return respuesta;
                }
            }

            System.out.println("Error, Solo puede ingresar S o N");
        }
    }
}
