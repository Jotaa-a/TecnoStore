package Utilidades;

import java.util.Scanner;

public class validarTelefono {
    private final Scanner sc = new Scanner (System.in);
    public String validarTelefono(String mensaje) {
    String telefono;

    do {
        System.out.println(mensaje + ": ");
        telefono = sc.nextLine().trim();

        if (!telefono.matches("\\d{7,15}")) {
            System.out.println("⚠ El teléfono debe contener únicamente números (7 a 15 dígitos).");
            continue;
        }

        return telefono;

    } while (true);
}
}
