package Utilidades;

import java.util.Scanner;
import java.util.regex.Pattern;

public class validarIdentificacion {

    private static final Pattern PATRON_IDENTIFICACION = Pattern.compile("^^[A-Za-z0-9]+$");
    private final Scanner sc = new Scanner(System.in);

    public String validarIdentificacion(String mensaje) {
        while (true) {
            System.out.println(mensaje + ": ");
            String identificacion = sc.nextLine().strip();

            if (identificacion.equals(" ")) {
                System.out.println("Ingrese una identificación válida");
            }

            if (PATRON_IDENTIFICACION.matcher(identificacion).matches()) {
                return identificacion;
            }

            System.out.println("Identificación no válida");
        }
    }
}
