package Utilidades;

import java.util.Scanner;

public class validarString {
     private static final Scanner sc = new Scanner(System.in);
     
     public String validarTexto(String mensaje) {
        String texto;

        do {
            System.out.print(mensaje);
            texto = sc.nextLine().trim();

            if (texto.isEmpty()) {
                System.out.println("⚠ El campo no puede estar vacío.");
            }

        } while (texto.isEmpty());

        return texto;
    }

    public String validarNombre(String mensaje) {
        String texto;

        do {
            System.out.println(mensaje + ": ");
            texto = sc.nextLine().trim();

            if (texto.isEmpty()) {
                System.out.println("⚠ El campo no puede estar vacío.");
                continue;
            }

            if (!texto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                System.out.println("⚠ Solo se permiten letras.");
                continue;
            }

            return texto;

        } while (true);
    }
}
