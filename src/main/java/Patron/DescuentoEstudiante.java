package Patron;

public class DescuentoEstudiante implements StrategyDescuento{

    @Override
    public double calcular(double subtotal) {
        return subtotal * 0.9;
    }
    
}
