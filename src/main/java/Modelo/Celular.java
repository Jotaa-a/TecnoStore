package Modelo;




public class Celular {
    private int id_celular;
    private String marca;
    private String modelo;
    private int stock;
    private String sistema_operativo;
    private Gama gama;

    public Celular(int id_celular, String marca, String modelo, int stock, String sistema_operativo, Gama gama) {
        this.id_celular = id_celular;
        this.marca = marca;
        this.modelo = modelo;
        this.stock = stock;
        this.sistema_operativo = sistema_operativo;
        this.gama = gama;
    }

    public int getId_celular() {
        return id_celular;
    }

    public void setId_celular(int id_celular) {
        this.id_celular = id_celular;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getSistema_operativo() {
        return sistema_operativo;
    }

    public void setSistema_operativo(String sistema_operativo) {
        this.sistema_operativo = sistema_operativo;
    }

    public Gama getGama() {
        return gama;
    }

    public void setGama(Gama gama) {
        this.gama = gama;
    }
    
    
}
