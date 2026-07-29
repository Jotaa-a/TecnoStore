package Modelo;

public class VentasMes {
    private  String mes;
    private double total;
    
    public VentasMes(String mes, double total){
        this.mes = mes;
        this.total = total;
    }

    @Override
    public String toString() {
        return mes + " -> $" + String.format("%,.0f", total);
    }
    
    
}
