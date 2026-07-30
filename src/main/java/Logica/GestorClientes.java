package Logica;

import Modelo.Cliente;
import Persistencia.ClientesDAO;
import java.util.ArrayList;
import Utilidades.*;
import java.util.Scanner;



public class GestorClientes {
    
    private validarIdentificacion vI = new validarIdentificacion();
    private validarCorreo vC = new validarCorreo();
    private validarString vS = new validarString();
    private validarEntero vInt = new validarEntero();
    private validarChar vCh = new validarChar();
    private validarTelefono vT = new validarTelefono();
    private ClientesDAO cDAO = new ClientesDAO();
    private Scanner sc = new Scanner(System.in);
    
    public void registrarCLiente(){
       String identificacion = vI.validarIdentificacion("Ingrese la identificación del cliente");
        ArrayList<ItemVenta> carrito = new ArrayList<>();
        Cliente cliente = cDAO.buscarPorIdentificacion(identificacion);
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
            cDAO.insert(cliente);
            cliente = cDAO.buscarPorIdentificacion(cliente.getIdentificacion());
        }
    }
    
    public void actualizarCliente(){
        ArrayList<Cliente> clientes = cDAO.listar();
        
        if(clientes.isEmpty()){
            System.out.println("No hay celulares registrados");
        }
        
        System.out.println("================= CLIENTES ==================");
        clientes.forEach(c -> System.out.println( c.getId()+" - " 
                + c.getNombre() + " | " 
                + c.getCorreo() + " | "
                + c.getIdentificacion()
                ));
         int idCliente = vInt.validarEntero("Ingrese el ID del cliente");
        
        Cliente cliente = cDAO.buscar(idCliente);
        
        if(cliente == null){
            System.out.println("El cliente no existe");
            return;
        }
        
        System.out.println("""
                           ====== DATOS ACTUALES ======
                           Nombre:          %s
                           Correo:          %s
                           Telefono:        %s
                           Identificacion:  %s
                           """.formatted(cliente.getNombre(),
                                   cliente.getCorreo(), 
                                   cliente.getTelefono(),
                                   cliente.getIdentificacion()));
        
        String nombre = vS.validarNombre("Nuevo nombre");
        
        String correo = vC.validarCorreo("Nuevo correo");
        
        String telefono = vT.validarTelefono("Nuevo telefono");
        
        String identificacion = vI.validarIdentificacion("Nueva identificacion");
        
        cliente.setNombre(nombre);
        cliente.setCorreo(correo);
        cliente.setTelefono(telefono);
        cliente.setIdentificacion(identificacion);
        
        cDAO.update(cliente);
        
        System.out.println("CLiente actualizado correctamente");
    }
    
    public void  eliminarCliente(){
        ArrayList<Cliente> clientes = cDAO.listar();
        
        if(clientes.isEmpty()){
            System.out.println("No hay celulares registrados");
        }
        
        System.out.println("================= CLIENTES ==================");
        clientes.forEach(c -> System.out.println( c.getId()+" - " 
                + c.getNombre() + " | " 
                + c.getCorreo() + " | "
                + c.getIdentificacion()
            ));
         int idCliente = vInt.validarEntero("Ingrese el ID del cliente");
        
        Cliente cliente = cDAO.buscar(idCliente);
        
        if(cliente == null){
            System.out.println("Cliente no encontrado");
            return;
        }
        
        System.out.println("""
                           ===== CLIENTE SELECCIONADO =====
                           Nombre:          %s
                           Correo:          %s
                           Identificacion:  %s
                           """.formatted(cliente.getNombre(),
                                   cliente.getCorreo(),
                                   cliente.getIdentificacion()
                           ));
        
        char op =  vCh.validarSN("¿Desea eliminar este cliente? (S/N)");
        
        if(op == 'N'){
            System.out.println("Operación cancelada.");
            return;
        }
        
        boolean eliminado = cDAO.delete(cliente);
        
        if (eliminado) {
            System.out.println("Cliente eliminado con exito");
        } else {
            System.out.println("No se pudo eliminar el cliente");
        }
    }
    
    public void listarClientes(){
        ArrayList<Cliente> modelos = cDAO.listar();
        
        modelos.forEach(c -> System.out.println(c.getId() + " - "
                + c.getNombre() + " | "   
                + c.getCorreo() + " | "
                + c.getTelefono() + " | "
        ));
    }
}