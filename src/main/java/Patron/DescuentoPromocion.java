package Patron;

public class DescuentoPromocion implements StrategyDescuento {

    @Override
    public double calcular(double subtotal) {
        return subtotal * 0.65;
    }
    
}
