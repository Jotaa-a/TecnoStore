package Modelo;




public class Celular {
    private int id_celular;
    private ModeloCelular modelo;
    private double precio;
    private int stock;

    public Celular(int id_celular, ModeloCelular modelo, double precio, int stock) {
        this.id_celular = id_celular;
        this.modelo = modelo;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId_celular() {
        return id_celular;
    }

    public void setId_celular(int id_celular) {
        this.id_celular = id_celular;
    }

    public ModeloCelular getModelo() {
        return modelo;
    }

    public void setModelo(ModeloCelular modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    
    
    
}
