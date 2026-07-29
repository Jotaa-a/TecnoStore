package Patron;

public class DescuentoEmpleado implements StrategyDescuento {

    @Override
    public double calcular(double subtotal) {
        return subtotal * 0.75;
    }
    
}
