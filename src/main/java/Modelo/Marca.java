package Modelo;




public class Marca {
    private int id_mk;
    private String nombre;
    
    public Marca(int id_mk, String nombre){
        this.id_mk = id_mk;
        this.nombre = nombre;
    }

    public int getId_mk() {
        return id_mk;
    }

    public void setId_mk(int id_mk) {
        this.id_mk = id_mk;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
