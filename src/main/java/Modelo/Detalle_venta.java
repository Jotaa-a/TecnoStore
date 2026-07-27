package Modelo;




public class Detalle_venta {
    private int id_dv;
    private Ventas id_v;
    private Celular id_celular;
    private int cantidad;
    private double subtotal;

    public Detalle_venta(int id_dv, Ventas id_v, Celular id_celular, int cantidad, double subtotal) {
        this.id_dv = id_dv;
        this.id_v = id_v;
        this.id_celular = id_celular;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public int getId_dv() {
        return id_dv;
    }

    public void setId_dv(int id_dv) {
        this.id_dv = id_dv;
    }

    public Ventas getId_v() {
        return id_v;
    }

    public void setId_v(Ventas id_v) {
        this.id_v = id_v;
    }

    public Celular getId_celular() {
        return id_celular;
    }

    public void setId_celular(Celular id_celular) {
        this.id_celular = id_celular;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    
}
