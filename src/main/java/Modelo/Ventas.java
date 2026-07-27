package Modelo;

import java.util.Date;


public class Ventas {
    public int id_v;
    private Cliente cliente;
    private Date fecha;
    private double total;

    public Ventas(int id_v, Cliente cliente, Date fecha, double total) {
        this.id_v = id_v;
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;
    }

    public int getId_v() {
        return id_v;
    }

    public void setId_v(int id_v) {
        this.id_v = id_v;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
