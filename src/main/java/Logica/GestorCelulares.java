package Logica;

import Modelo.Celular;
import Modelo.ModeloCelular;
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
    private validarDecimal vD = new validarDecimal();
    private validarChar vCh = new validarChar();

    
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

        ModeloCelular modelo = modeloDAO.buscar(idModelo);

        if (modelo == null) {
            System.out.println("El modelo no existe.");
            return;
        }

        double precio = vD.validarDouble("Ingrese el precio:");

        int stock = vE.validarEntero("Ingrese el stock:");

        Celular celular = new Celular(
                0,
                modelo,
                precio,
                stock
        );

        cDAO.insert(celular);

        System.out.println("\nCelular registrado correctamente.");
    }
    
    public void actualizarCelular(){
        ArrayList<Celular> celulares = cDAO.listar();
        
        if(celulares.isEmpty()){
            System.out.println("No hay celulares registrados");
            return;
        }
        
        System.out.println("====== CELULARES ======");
        celulares.forEach(c -> System.out.println(
            c.getId_celular() + " - "
            + c.getModelo().getMarca().getNombre() + " "
            + c.getModelo().getNombre()
            + " | $" + c.getPrecio()
            + " | Stock: " + c.getStock()
        ));
        
        int idCelular = vE.validarEntero("Ingrese el ID del celular");
        
        Celular celular = cDAO.buscar(idCelular);
        
        if(celular == null){
            System.out.println("El celular no existe");
            return;
        }
        
        System.out.println("""
                           ====== DATOS ACTUALES ======
                           Modelo:  %s
                           Precio:  %s
                           Stock:   %s
                           """.formatted(celular.getModelo().getNombre(),
                                   celular.getPrecio(), 
                                   celular.getStock()));
        
        System.out.println("====== MODELOS ======");
        modeloDAO.listar().forEach(m -> System.out.println(m.getId_md() + " - "  
                + m.getMarca().getNombre() + " - "
                + m.getNombre()
        ));
        
        int idModelo = vE.validarEntero("Nuevo modelo");
        
        ModeloCelular modelo = modeloDAO.buscar(idModelo);
        
        if(modelo == null){
            System.out.println("Modelo no encontrado");
        }
        
        double precio = vD.validarDouble("Nuevo precio");
        
        int stock = vE.validarEntero("Nuevo stock");
        
        celular.setModelo(modelo);
        celular.setPrecio(precio);
        celular.setStock(stock);
        
        cDAO.update(celular);
        
        System.out.println("Celular actualizado correctamente");
    }
    
    public void eliminarCelular(){
        ArrayList<Celular> celulares = cDAO.listar();
        
        if(celulares.isEmpty()){
            System.out.println("No hay celulares registrados");
            return;
        }
        
        System.out.println("====== CELULARES ======");
        celulares.forEach(c -> System.out.println(
            c.getId_celular() + " - "
            + c.getModelo().getMarca().getNombre() + " "
            + c.getModelo().getNombre()
            + " | $" + c.getPrecio()
            + " | Stock: " + c.getStock()
        ));
        
        int idCelular = vE.validarEntero("Ingrese el ID del celular");
        
        Celular celular = cDAO.buscar(idCelular);
        
        if(celular == null){
            System.out.println("El celular no existe");
            return;
        }
        
        System.out.println("""
                           ===== CELULAR SELECCIONADO =====
                           Modelo:      %s
                           Precio: $    %s
                           Stock:       %s
                           """.formatted(celular.getModelo().getNombre(),
                                   celular.getPrecio(),
                                   celular.getStock()
                        ));
        
        
        
        char op = vCh.validarSN("¿Desea eliminar este celular? (S/N)");

        if(op == 'N'){
            System.out.println("Operación cancelada.");
            return;
        }
        
        boolean eliminado = cDAO.delete(celular);
        
        if (eliminado) {
            System.out.println("Celular eliminado con exito");
        } else {
            System.out.println("No se pudo eliminar el celular");
        }
    }
    
    public void listarCelulares(){
        ArrayList<ModeloCelular> modelos = modeloDAO.listar();
        
        modelos.forEach(m -> System.out.println(m.getId_md() + " - "  
                + m.getMarca().getNombre() + " - "
                + m.getNombre()
        ));
    }
    
   
}