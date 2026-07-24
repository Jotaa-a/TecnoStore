package Utilidades;

import java.util.Scanner;
import java.util.regex.Pattern;

public class validarCorreo {
    
    private static final Pattern PATRON_CORREO = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private final Scanner sc = new Scanner(System.in);

    public String validarCorreo(String mensaje) {
        while (true) {
            System.out.println(mensaje + ": ");
            String correo = sc.nextLine();

            if (PATRON_CORREO.matcher(correo).matches()) {
                return correo;
            }

            System.out.println("Correo electronico no válido");
        }
    }
}
