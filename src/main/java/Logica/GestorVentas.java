package Logica;

import Modelo.Cliente;
import Persistencia.CelularesDAO;
import Persistencia.ClientesDAO;
import Persistencia.VentasDAO;
import Utilidades.validarIdentificacion;


public class GestorVentas {
    private validarIdentificacion vI = new validarIdentificacion();
    private ClientesDAO clientesDAO = new ClientesDAO();
    private CelularesDAO celularesDAO = new CelularesDAO();
    private VentasDAO ventasDAO = new VentasDAO();
    
    public void registrarVenta(){
        String identificacion = vI.validarIdentificacion("Ingrese la identificación del cliente");
        
        Cliente cliente = clientesDAO.buscarPorIdentificacion(identificacion);
        
        if(cliente != null){
            System.out.println("Cliente encontrado: "+cliente.getNombre());
        }
        
        if(cliente == null){
            System.out.println("Cliente no registrado");
        }
    }
}
    

