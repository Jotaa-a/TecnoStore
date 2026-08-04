package Logica;

import Modelo.Celular;
import Modelo.Cliente;
import Persistencia.*;
import Utilidades.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class GestorVentas {

    private validarIdentificacion vI = new validarIdentificacion();
    private validarCorreo vC = new validarCorreo();
    private validarEntero vE = new validarEntero();
    private validarChar vCh = new validarChar();
    private ClientesDAO clientesDAO = new ClientesDAO();
    private CelularesDAO celularesDAO = new CelularesDAO();
    private ReportesDAO reportesDAO = new ReportesDAO();
    private VentasDAO ventasDAO = new VentasDAO();
    private Scanner sc = new Scanner(System.in);
    private GestorClientes gC = new GestorClientes();

    public void registrarVenta() throws IOException {
        String identificacion = vI.validarIdentificacion("Ingrese la identificación del cliente");
        ArrayList<ItemVenta> carrito = new ArrayList<>();
        Cliente cliente = clientesDAO.buscarPorIdentificacion(identificacion);
        char op = 0;
        
        if (cliente != null) {
            System.out.println("Cliente encontrado: " + cliente.getNombre());
        }

        if (cliente == null) {
            gC.registrarCLiente();
        }
        
        do {
            celularesDAO.listar()
                        .stream()
                        .forEach(celular -> System.out.println(
                            celular.getId_celular() + " - "+
                            celular.getModelo().getNombre() + " - "+
                            " - $" + celular.getPrecio() + 
                            " - Stock: "+ celular.getStock()
                    ));
            
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
        
        System.out.println("\n==== RESUMEN DE LA VENTA ====");
        System.out.println("Cliente: "+cliente.getNombre());
        System.out.println();
        
        double subtotal = carrito.stream()
                .mapToDouble(ItemVenta::getSubtotal)
                .sum();
        
        for(ItemVenta item: carrito){
            System.out.printf("Celular: %s | Cantidad: %s | Precio: $%,.0f | Subtotal: $%,.0f%n",
                                item.getCelular().getModelo().getNombre(),
                                item.getCantidad(),
                                item.getCelular().getPrecio(),
                                item.getSubtotal()
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
                ReporteUtils reporte = new ReporteUtils();
                reporte.generarReporteVentas();
            } else {
                System.out.println("Error al registrar la venta!");
            }
        } else {
            System.out.println("Venta cancelada.");
        }
    }
    
    
}