package Modelo;

public class TopVendidos {
    private String modelo;
    private int vendidos;
    
    public TopVendidos(String modelo, int vendidos){
        this.modelo = modelo;
        this.vendidos = vendidos;
    }

    @Override
    public String toString() {
        return modelo + " - " + vendidos + " vendidos";
    }
    
    
}
