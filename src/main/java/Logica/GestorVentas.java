package Logica;

import Modelo.Celular;
import Modelo.Cliente;
import Persistencia.*;
import Utilidades.*;
import java.util.Scanner;
import java.util.ArrayList;

public class GestorVentas {

    private validarIdentificacion vI = new validarIdentificacion();
    private validarCorreo vC = new validarCorreo();
    private validarEntero vE = new validarEntero();
    private validarChar vCh = new validarChar();
    private ClientesDAO clientesDAO = new ClientesDAO();
    private CelularesDAO celularesDAO = new CelularesDAO();
    private VentasDAO ventasDAO = new VentasDAO();
    private Scanner sc = new Scanner(System.in);

    public void registrarVenta() {
        String identificacion = vI.validarIdentificacion("Ingrese la identificación del cliente");
        ArrayList<ItemVenta> carrito = new ArrayList<>();
        Cliente cliente = clientesDAO.buscarPorIdentificacion(identificacion);
        char op = 0;
        
        if (cliente != null) {
            System.out.println("Cliente encontrado: " + cliente.getNombre());
        }

        if (cliente == null) {
            System.out.println("Cliente no registrado");
            System.out.println("Ingrese el nombre:");
            String nombre = sc.nextLine();
            String correo = vC.validarCorreo("Ingrese el correo");
            System.out.println("Ingrese el telefono:");
            String telefono = sc.nextLine();

            cliente = new Cliente(0, nombre, identificacion, correo, telefono);
            clientesDAO.insert(cliente);
            cliente = clientesDAO.buscarPorIdentificacion(cliente.getIdentificacion());
        }
        
        do {
            ArrayList<Celular> celulares = celularesDAO.listar();
            for(Celular celular : celulares){
                System.out.println(
                        celular.getId_celular() + " - " +
                        celular.getModelo().getNombre() + 
                        " - $" + celular.getPrecio() + 
                        " - Stock: " + celular.getStock()
                );
            }
            int id = vE.validarEntero("Ingrese el id del celular");
            Celular celular = celularesDAO.buscar(id);
            if (celular == null) {
                System.out.println("El celular con id "+id+" no existe"); 
                continue;
            }
            
            int cantidad = vE.validarEntero("ingrese la cantidad");
            
            if (celular.getStock() < cantidad) {
                System.out.println("Stock insuficiente para la venta");
                continue;
            }
            
            carrito.add(new ItemVenta(celular, cantidad));
            op = vCh.validarSN("Desea agregar otro celular S/N?");
        } while (op == 'S');
        
        if(carrito.isEmpty()){
            System.out.println("No se agregaron productos a la venta");
            return;
        }
        
        double subtotal = 0;
        
        System.out.println("\n==== RESUMEN DE LA VENTA ====");
        System.out.println("Cliente: "+cliente.getNombre());
        System.out.println();
        
        for(ItemVenta item: carrito){
            
            double sub = item.getSubtotal();
            subtotal += sub;
            
            System.out.println("Celular: "+ item.getCelular().getModelo().getNombre()+
                                " | Cantidad: "+ item.getCantidad()+
                                " | Precio: "+item.getCelular().getPrecio()+
                                " | Subtotal: $%,.0f%n"+sub
            );
        }
        
        double iva = subtotal * 0.19;
        double total = subtotal + iva;
        
        System.out.println("Subtotal: $" + subtotal);
        System.out.println("IVA (19%): $" + iva);
        System.out.printf("TOTAL: $%,.0f%n", total);
        
        char confirmar = vCh.validarSN("¿Confirmar venta? (S/N)");

        if (confirmar == 'S') {
            boolean registrada = ventasDAO.registrarVenta(cliente, carrito, total);
            
            if(registrada){
                System.out.println("Venta registrada con exito!");
            } else {
                System.out.println("Error al registrar la venta!");
            }
        } else {
            System.out.println("Venta cancelada.");
        }
    }
    
    
}
