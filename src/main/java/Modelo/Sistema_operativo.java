package Modelo;




public class Sistema_operativo {
    private int id_so;
    private String nombre;

    public Sistema_operativo(int id_so, String nombre) {
        this.id_so = id_so;
        this.nombre = nombre;
    }

    public int getId_so() {
        return id_so;
    }

    public void setId_so(int id_so) {
        this.id_so = id_so;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
