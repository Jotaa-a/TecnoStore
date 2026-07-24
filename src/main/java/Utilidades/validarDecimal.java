package Utilidades;

import java.util.Scanner;

public class validarDecimal {

    private final Scanner sc = new Scanner(System.in);

    public double validarDouble(String mensaje) {

        while (true) {
            try {
                System.out.print(mensaje+": ");
                double dato = Double.parseDouble(sc.nextLine());

                if (dato > 0.0) {
                    return dato;
                }

                System.out.println("Debe ingresar un número positivo.");
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido");
            }
        }
    }
}
