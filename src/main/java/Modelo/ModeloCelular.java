package Modelo;




public class ModeloCelular {
    private int id_md;
    private Marca marca;
    private Sistema_operativo so;
    private String nombre;
    private Gama gama;

    public ModeloCelular(int id_md, Marca marca, Sistema_operativo so, String nombre, Gama gama) {
        this.id_md = id_md;
        this.marca = marca;
        this.so = so;
        this.nombre = nombre;
        this.gama = gama;
    }

    public int getId_md() {
        return id_md;
    }

    public void setId_md(int id_md) {
        this.id_md = id_md;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Sistema_operativo getSo() {
        return so;
    }

    public void setSo(Sistema_operativo so) {
        this.so = so;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Gama getGama() {
        return gama;
    }

    public void setGama(Gama gama) {
        this.gama = gama;
    }
    
    
}
