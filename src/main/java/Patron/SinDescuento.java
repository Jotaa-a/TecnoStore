package Patron;

public class SinDescuento implements StrategyDescuento {

    @Override
    public double calcular(double subtotal) {
        return subtotal;
    }
    
}
