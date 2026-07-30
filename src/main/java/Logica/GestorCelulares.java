package Logica;

import Modelo.Celular;
import Modelo.ModeloCelular;
import Modelo.TopVendidos;
import Patron.FactoryCelular;
import Persistencia.*;
import Utilidades.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorCelulares {

    private final ReportesDAO rDAO = new ReportesDAO();
    private final CelularesDAO cDAO = new CelularesDAO();
    private final ModeloDAO modeloDAO = new ModeloDAO();
    
    private final FactoryCelular factory = new FactoryCelular();
    
    private final Scanner sc = new Scanner (System.in);
    
    private validarEntero vE = new validarEntero();

    public void registrarCelular() {
        ArrayList<ModeloCelular> modelos = modeloDAO.listar();

        if (modelos.isEmpty()) {
            System.out.println("No existen modelos registrados.");
            return;
        }
        
        System.out.println("\n===== MODELOS DISPONIBLES =====");
        for (ModeloCelular modelo : modelos) {
            System.out.println(
                    modelo.getId_md() + " - "
                    + modelo.getMarca().getNombre() + " "
                    + modelo.getNombre()
                    + " (" + modelo.getSo().getNombre() + ")"
                    + " - Gama: " + modelo.getGama()
            );

        }

        int idModelo = vE.validarEntero("Seleccione el ID del modelo:");

        // Buscar el modelo
        ModeloCelular modelo = modeloDAO.buscar(idModelo);

        if (modelo == null) {
            System.out.println("El modelo no existe.");
            return;
        }

        // Pedir precio
        double precio = vD.validarDouble("Ingrese el precio:");

        // Pedir stock
        int stock = vE.validarEntero("Ingrese el stock:");

        // Crear el celular
        Celular celular = new Celular(
                0,
                modelo,
                precio,
                stock
        );

        // Guardar en la BD
        celularesDAO.insert(celular);

        System.out.println("\nCelular registrado correctamente.");
    }

    
    public void actualizarCelular(){
        
    }
    
    public void eliminarCelular(){
        
    }
    
    public void listarCelulares(){
        
    }
    
    public void stockCelulares() {
        rDAO.stockBajo().stream().forEach(c -> System.out.println(
                c.getModelo().getNombre() + 
                " - Stock: "+ c.getStock()
        ));
    }
    
    public void topVendidos(){
        for (TopVendidos tV : rDAO.topVentas()){
            System.out.println(tV);
        }
    }
}
