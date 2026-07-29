package Patron;

public class DescuentoVip implements StrategyDescuento {

    @Override
    public double calcular(double subtotal) {
        return subtotal * 0.8;
    }
    
}
